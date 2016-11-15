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

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.sql.entry.Entry;

public class BinderProxyTest {

	@Test
	public void testProxyBinderStatment() {
		Entry entry = new Entry().set("CountryCode", "MAR").set("District", "Fès-Boulemane");
		ConnectionManager cm = ContextLoader.get();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			BinderProxy<Entry> binder = BinderProxy.get(EntryMultiBinder.class, "findCityByCountryAndDistrict");
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(Queries.query1, entry, binder);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, Queries.query1, entry, binder);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testProxyBinderStatment2() {
		String methodName = "findCityByXOrY";
		Entry entry = new Entry();
		entry.set("CountryCode", "MAR");
		entry.set("District", "Fès-Boulemane");
		ConnectionManager cm = ContextLoader.get();
		Statement stmt = null;
		try {
			BinderProxy<Entry> binder = BinderProxy.get(EntryMultiBinder.class, methodName);
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(Queries.query1, entry, binder);
		} catch (Exception e) {
			
		}finally{
			assertNull(stmt);
			try {
				assertFalse(cm.isClosed());
				cm.close();
				assertTrue(cm.isClosed());
			} catch (SQLException e) {}
		}
	}
	
}
