package usf.java.sqlreflect.connection.manager;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.*;

import usf.java.sqlreflect.binder.BinderProxy;
import usf.java.sqlreflect.binder.ParameterBinder;
import usf.java.sqlreflect.connection.provider.ConnectionProvider;
import usf.java.sqlreflect.connection.provider.SimpleConnectionProvider;
import usf.java.sqlreflect.server.Server;
import usf.java.sqlreflect.sql.ParameterFactory;
import usf.java.sqlreflect.sql.Parameters;
import usf.java.sqlreflect.sql.SqlQuery;
import usf.java.sqlreflect.sql.entry.Entry;

public class SimpleConnectionManagerTest {
	
	private static Server server;
	private static ConnectionProvider cp;
	private static ConnectionManager cm;
	
	private static String query = "SELECT 1";
	private static String query1 = "SELECT * FROM city where CountryCode=? and District=?";
	
	private Statement stmt = null;
	private ResultSet rs = null;

	@Test
	public void testServer() {
		assertNotNull(cm.getServer());
	}

	@Test
	public void testOpenCloseConnection() {
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
	public void testExecStatment() {
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(query, null, null);
			assertTrue(stmt instanceof Statement);
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
			assertTrue(cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	@Test
	public void testExecPreparedStatment() {
		Parameters p = new Parameters(
				ParameterFactory.VARCHAR_WRAPPER("MAR"),
				ParameterFactory.VARCHAR_WRAPPER("Fès-Boulemane"));
		ParameterBinder pb = new ParameterBinder();
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(query1, p, pb);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, query1, p, pb);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Fès");
			cm.close(rs);
			assertTrue(rs.isClosed());
			assertFalse(cm.isClosed());
			cm.close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(cm.isClosed());
			cm.close();
			assertTrue(cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProxyBinderStatment() {
		Entry entry = new Entry();
		entry.set("CountryCode", "MAR");
		entry.set("District", "Fès-Boulemane");
		BinderProxy<Entry> binder = new BinderProxy<Entry>(new EntryBinder(), "findCityByCountryAndDistrict");
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(query1, entry, binder);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, query1, entry, binder);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Fès");
			cm.close(rs);
			assertTrue(rs.isClosed());
			assertFalse(cm.isClosed());
			cm.close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(cm.isClosed());
			cm.close();
			assertTrue(cm.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProxyBinderStatment2() {
		String methodName = "findCityByXOrY";
		Entry entry = new Entry();
		entry.set("CountryCode", "MAR");
		entry.set("District", "Fès-Boulemane");
		BinderProxy<Entry> binder = new BinderProxy<Entry>(new EntryBinder(), methodName);
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(query1, entry, binder);
		} catch (SQLException e) {
			
		}finally{
			assertNull(stmt);
			try {
				assertFalse(cm.isClosed());
				cm.close();
				assertTrue(cm.isClosed());
			} catch (SQLException e) {}
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
	
}
