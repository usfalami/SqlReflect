package usf.java.sqlreflect;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import usf.java.sqlreflect.adapter.ListWriter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.ConnectionManagerImpl;
import usf.java.sqlreflect.connection.manager.ConnectionManagerImplTest;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.connection.manager.TransactionManagerImpl;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.reflect.scanner.data.HeaderScanner;
import usf.java.sqlreflect.reflect.scanner.field.DatabaseScanner;
import usf.java.sqlreflect.reflect.scanner.field.TableScanner;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.stream.PrinterStreamWriter;

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
		PrinterStreamWriter ps = new PrinterStreamWriter(System.out);
		
		new DatabaseScanner(cm).run(new ListWriter<Database>(ps));
		new TableScanner(cm).set("mysql", null).run(new ListWriter<Table>(ps));
		new HeaderScanner<Void>(cm).set("show processlist").run(new ListWriter<Header>(ps));
		
	}
	
}
