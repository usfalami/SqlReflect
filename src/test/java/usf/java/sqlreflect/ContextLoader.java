package usf.java.sqlreflect;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManager;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManagerTest;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.server.Server;

public class ContextLoader {

	private static Server server;
	private static ConnectionProvider cp;
	private static ConnectionManager cm;

	public static void init(){		
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
			
			System.out.println("Env has been initialized");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@AfterClass
	public static void clean() {
		cm.close();
		System.out.println("Test end");
	}
	
	@Before
	public void before() {}

	@After
	public void after() {}
	
	public static ConnectionManager get() {
		if(cm == null) init(); // not clean but resolve singleton problems
		return cm;
	}
	
}
