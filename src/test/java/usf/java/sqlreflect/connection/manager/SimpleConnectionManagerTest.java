package usf.java.sqlreflect.connection.manager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.*;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.server.Server;

public class SimpleConnectionManagerTest {
	
	private static Server server;
	private static ConnectionProvider cp;
	private static ConnectionManager  cm;
	private static String query = "SELECT 1";
	
	private Statement stmt = null;
	private ResultSet rs = null;

	@BeforeClass
	public static void init(){		
		try {
			System.out.println("Start loading...");
			
			InputStream inputStream  = SimpleConnectionManagerTest.class.getClassLoader().getResourceAsStream("env.properties");
			Properties properties = new Properties();
			properties.load(inputStream);

			server = (Server) Class.forName(properties.getProperty("server")).newInstance();
			Class.forName(server.getDriver());

			cp = new SimpleConnectionProvider(server, properties);
			cm = new SimpleConnectionManager(cp, server);
			
			System.out.println("Env has been loaded");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testServer() {
		assertNotNull(cm.getServer());
	}

	@Test
	public void testConnectionStatus() {
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			Connection c = cm.getConnection();
			assertNotNull(c);
			assertFalse(c.isClosed());
			cm.openConnection();
			assertEquals(c, cm.getConnection());
			cm.close();
			assertTrue(cm.isClosed());
			assertTrue(c.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryExec() {
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(query, null, null);
			rs = cm.executeQuery(stmt, query, null, null);
			assertTrue(rs.next());
			assertEquals(rs.getInt(1), 1);
			cm.close(rs);
			assertTrue(rs.isClosed());
			assertFalse(cm.isClosed());
			cm.close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(cm.isClosed());
			cm.close();
			assertTrue("Connection is closed", cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Before
	public void beforeTest(){
		System.out.println("Start Test");
	}
	
	@After
	public void afterTest(){
		cm.close(rs);
		cm.close(stmt);
		cm.close();
		System.out.println("End Test");
	}

}
