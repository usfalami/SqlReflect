package usf.java.sqlreflect;

import java.io.InputStream;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import usf.java.sqlreflect.binder.ParameterBinderTest;
import usf.java.sqlreflect.binder.ProxyBinderTest;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManager;
import usf.java.sqlreflect.connection.manager.SimpleConnectionManagerTest;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.server.Server;

@RunWith(Suite.class)
@SuiteClasses({
	SimpleConnectionManagerTest.class, 
	ProxyBinderTest.class, 
	ParameterBinderTest.class})

public class AbstractTest {

	private static Server server;
	private static ConnectionProvider cp;
	private static ConnectionManager cm;

	@BeforeClass
	public static void init(){		
		try {
			System.out.println("Start loading...");
			
			InputStream inputStream  = SimpleConnectionManagerTest.class.getClassLoader().getResourceAsStream("env.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			server = (Server) Class.forName(properties.getProperty("server")).newInstance();
			//Class.forName(server.getDriver());

			cp = new SimpleConnectionProvider(server, properties);
			cm = new SimpleConnectionManager(cp, server);
			
			System.out.println("Env has been loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Before
	public void beforeTest(){
		System.out.println("Start Test");
	}
	@After
	public void afterTest(){
		System.out.println("End Test");
	}
	
	public static ConnectionManager getConnectionManager() {
		return cm;
	}
	
}
