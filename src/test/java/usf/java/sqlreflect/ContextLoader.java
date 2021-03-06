package usf.java.sqlreflect;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.ConnectionManagerImpl;
import usf.java.sqlreflect.connection.manager.ConnectionManagerImplTest;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.connection.manager.TransactionManagerImpl;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.mapper.ComplexProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.reflect.scanner.NativeFunctionScanner;
import usf.java.sqlreflect.reflect.scanner.data.GenericScanner;
import usf.java.sqlreflect.reflect.scanner.data.HeaderScanner;
import usf.java.sqlreflect.reflect.scanner.field.ColumnScanner;
import usf.java.sqlreflect.reflect.scanner.field.DatabaseScanner;
import usf.java.sqlreflect.reflect.scanner.field.ImportedKeyScanner;
import usf.java.sqlreflect.reflect.scanner.field.PrimaryKeyScanner;
import usf.java.sqlreflect.reflect.scanner.field.ProcedureScanner;
import usf.java.sqlreflect.reflect.scanner.field.TableScanner;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.NativeFunctions;
import usf.java.sqlreflect.sql.type.TableTypes;
import usf.java.sqlreflect.stream.PrinterStreamWriter;
import usf.java.sqlreflect.stream.StreamWriter;

public class ContextLoader {

	private static Server server;
	private static ConnectionProvider cp;
	
	private static ConnectionManager cm;
	private static TransactionManager tm;

	public static void load(){		
		try {
			System.out.println("Start loading env ...");
			
			InputStream inputStream  = ConnectionManagerImplTest.class.getClassLoader().getResourceAsStream("env.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			server = (Server) Class.forName(properties.getProperty("server")).newInstance();
			System.out.println("\t" + server.getClass().getName());
			//Class.forName(server.getDriver());

			cp = new SimpleConnectionProvider(server, properties);
			System.out.println("\t" + cp.getClass().getName());
			
			cm = new ConnectionManagerImpl(cp, server);
			System.out.println("\t" + cm.getClass().getName());
			
			tm = new TransactionManagerImpl(cp, server);
			System.out.println("\t" + tm.getClass().getName());
			
			System.out.println("Env has been initialized");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionManager getConnectionManager() {
		if(Utils.isNull(cm)) load(); // not clean but resolve singleton problems
		return cm;
	}
	
	public static TransactionManager getTransactionManager(){
		if(Utils.isNull(tm)) load(); // not clean but resolve singleton problems
		return tm;
	}
	
	public static void forceCloseConnectionManager(){
		close(cm);
	}
	public static void forceCloseTransactionManager(){
		close(tm);
	}
	
	protected static void close(ConnectionManager cm) {
		Connection c = null;
		try {
			c = cm.getConnection();
			throw new Exception("GRAVE : Connection still open");
		} catch (SQLException e) {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if(Utils.isNotNull(c)){
				try {
					c.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else System.out.println("Connection are closed");
		}
	}
	
	public static void main(String[] args) throws Exception {
		ConnectionManager cm = getConnectionManager();
//		StreamWriter ps = new JsonStreamWriter(c);
		StreamWriter ps = new PrinterStreamWriter(System.out); 
		
//		ps = new DebugProxyStream<StreamWriter>(ps); //debug
		ps.start();
		
		String query = "SELECT * FROM country";
		
		//[NativeFunction]	Date 
		new NativeFunctionScanner(cm).set(NativeFunctions.TIME_DATE).writeAll(ps);
		//[database]	select * 
		new DatabaseScanner(cm).writeAll(ps);
		//[Table] 		select mysql.time_zone%
		new TableScanner(cm).set("mysql", "time_zone%").writeAll(ps);
		//[View] 		sys.%io
		new TableScanner(cm).set("sys", "%io", TableTypes.VIEW).writeAll(ps);
		//[Column] 		world_x .*
		new ColumnScanner(cm).set("world_x", null, null).writeAll(ps);
		//[Procedure] 			sys.*
		new ProcedureScanner(cm).set("sys", null).writeAll(ps);
		//[PK] 			country
		new PrimaryKeyScanner(cm).set(null, "country").writeAll(ps);
		//[FK]	
		new ImportedKeyScanner(cm).set(null, "countrylanguage").writeAll(ps);
		//[Row] 		SELECT * FROM country
		new GenericScanner<Void>(cm).set(query).writeAll(ps);
		//[Header] 		SELECT * FROM country
		new HeaderScanner<Void>(cm).set(query).writeAll(ps);
		
		ex1();

		ps.end();
		forceCloseConnectionManager();
	}
	
	public static void ex1() throws Exception{

		StreamWriter ps = new PrinterStreamWriter(System.out); 
		
		ComplexProperty<Table> template = new ComplexProperty<Table>(Table.class);
		template.getFields().add(new SimpleProperty<String>("name", SqlConstants.TABLE_NAME));
		template.getFields().add(new SimpleProperty<String>("type", SqlConstants.TABLE_TYPE));
		template.getFields().add(new SimpleProperty<String>("databaseName", DatabaseType.CATALOG.TABLE_DATABASE));

		SimpleObjectMapper<Table> mapper = new SimpleObjectMapper<Table>(template);
		TableScanner ts = new TableScanner(getConnectionManager());
		ts.setMapper(mapper);
		ts.writeAll(ps);
	}
	
}
