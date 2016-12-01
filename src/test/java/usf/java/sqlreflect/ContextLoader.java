package usf.java.sqlreflect;

import java.io.InputStream;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import usf.java.sqlreflect.adapter.FullWriter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.ConnectionManagerImpl;
import usf.java.sqlreflect.connection.manager.ConnectionManagerImplTest;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.connection.manager.TransactionManagerImpl;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.mapper.tmp.AdvancedEntryMapper;
import usf.java.sqlreflect.mapper.tmp.IndexEnumFilter;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.reflect.scanner.field.ProcedureScanner;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Procedure;
import usf.java.sqlreflect.sql.type.ProcedureTypes;
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
		StringWriter c = new StringWriter();
//		StreamWriter ps = new XmlStreamWriter(c);
//		StreamWriter ps = new JsonStreamWriter(c);
		StreamWriter ps = new PrinterStreamWriter(System.out);
		
//		ps = new DebugProxyStream<StreamWriter>(ps); //debug
		
		ps.start();
		
		String query = "SELECT * FROM country";
		
//		new DatabaseScanner(cm).run(new ListWriter<Database>(ps));
//		new TableScanner(cm).set("mysql", "time_zone%").run(new FullWriter<Table>(ps));
//		new TableScanner(cm).set("sys", "%io", false, TableTypes.VIEW).run(new FullWriter<Table>(ps));
//		new HeaderScanner<Void>(cm).set("show processlist").run(new FullWriter<Header>(ps));
//		new PrimaryKeyScanner(cm).set(null, "country").run(new FullWriter<PrimaryKey>(ps));
		
		AdvancedEntryMapper<Procedure> mapper = new AdvancedEntryMapper<Procedure>(Procedure.class, "PROCEDURE_NAME", "PROCEDURE_TYPE");
		mapper.addMapperFilter(SqlConstants.PROCEDURE_TYPE, null, new IndexEnumFilter<ProcedureTypes>(ProcedureTypes.class));
		mapper.addMapperFilter(cm.getServer().getDatabaseType().PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME, null);

		ProcedureScanner proc = new ProcedureScanner(cm);
		proc.setMapper(mapper);
		proc.set("sys", "%").run(new FullWriter<Procedure>(ps));
		
		
//		new ProcedureScanner(cm).set("sys", "%").run(new FullWriter<Procedure>(ps));
		
//		Mapper<Entry> mapper = new usf.java.sqlreflect.mapper.tmp.EntryMapper<Entry>(Entry.class);

//		Mapper<Entry> mapper = new usf.java.sqlreflect.mapper.EntryMapper<Entry>(Entry.class);

//		RowScanner<Void, Entry> rs = new RowScanner<Void, Entry>(cm, mapper);
//		rs.set(query).run(new FullWriter<Entry>(ps));
		
//		new HeaderScanner<Void>(cm).set(query).run(new FullWriter<Header>(ps));
//		
		ps.end();
		System.out.println(c);
		forceCloseConnectionManager();
	}
	
}
