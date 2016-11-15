package usf.java.sqlreflect.binder;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import usf.java.sqlreflect.AbstractTest;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.connection.manager.EntryBinder;
import usf.java.sqlreflect.sql.entry.Entry;

public class ProxyBinderTest {
	
	private Statement stmt = null;
	private ResultSet rs = null;
	
	@Test
	public void testProxyBinderStatment() {
		Entry entry = new Entry();
		entry.set("CountryCode", "MAR");
		entry.set("District", "Fès-Boulemane");
		BinderProxy<Entry> binder = new BinderProxy<Entry>(EntryBinder.class, "findCityByCountryAndDistrict");
		try {
			assertTrue(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().openConnection();
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			stmt = AbstractTest.getConnectionManager().prepare(Queries.query1, entry, binder);
			assertTrue(stmt instanceof PreparedStatement);
			rs = AbstractTest.getConnectionManager().executeQuery(stmt, Queries.query1, entry, binder);
			assertTrue(rs.next());
			assertEquals(rs.getString("Name"), "Fès");
			AbstractTest.getConnectionManager().close(rs);
			assertTrue(rs.isClosed());
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().close(stmt);
			assertTrue(stmt.isClosed());
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().close();
			assertTrue(AbstractTest.getConnectionManager().isClosed());
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
		BinderProxy<Entry> binder = new BinderProxy<Entry>(EntryBinder.class, methodName);
		try {
			assertTrue(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().openConnection();
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			stmt = AbstractTest.getConnectionManager().prepare(Queries.query1, entry, binder);
		} catch (SQLException e) {
			
		}finally{
			assertNull(stmt);
			try {
				assertFalse(AbstractTest.getConnectionManager().isClosed());
				AbstractTest.getConnectionManager().close();
				assertTrue(AbstractTest.getConnectionManager().isClosed());
			} catch (SQLException e) {}
		}
	}
	
}
