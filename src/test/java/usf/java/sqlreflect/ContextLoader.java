package usf.java.sqlreflect;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManager;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManagerTest;
import usf.java.sqlreflect.connection.manager.SimpleTransactionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.server.Server;

public class ContextLoader {

	private static Server server;
	private static ConnectionProvider cp;
	
	private static ConnectionManager cm;
	private static TransactionManager tm;

	public static void load(){		
		try {
			System.out.println("Start loading env ...");
			
			InputStream inputStream  = SimpleConnectionManagerTest.class.getClassLoader().getResourceAsStream("env.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			server = (Server) Class.forName(properties.getProperty("server")).newInstance();
			System.out.println("\t" + server.getClass().getName());
			//Class.forName(server.getDriver());

			cp = new SimpleConnectionProvider(server, properties);
			System.out.println("\t" + cp.getClass().getName());
			
			cm = new SimpleConnectionManager(cp, server);
			System.out.println("\t" + cm.getClass().getName());
			
			tm = new SimpleTransactionManager(cp, server);
			System.out.println("\t" + tm.getClass().getName());
			
			System.out.println("Env has been initialized");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionManager getConnectionManager() {
		if(cm == null) load(); // not clean but resolve singleton problems
		return cm;
	}
	
	public static TransactionManager getTransactionManager(){
		if(tm == null) load(); // not clean but resolve singleton problems
		return tm;
	}
	
	public static void closeConnectionManager(){
		try {
			cm.getConnection().close();
		} catch (SQLException e) {}
		
	}
	public static void closeTransactionManager(){
		try {
			tm.getConnection().close();
		} catch (SQLException e) {}
		
	}
}
