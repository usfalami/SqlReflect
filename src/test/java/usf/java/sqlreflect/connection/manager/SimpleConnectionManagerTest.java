package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;

public class SimpleConnectionManagerTest   {

	@Test
	public void testServer() {
		assertNotNull(ContextLoader.get().getServer());
	}

	@Test
	public void testOpenCloseConnection() {
		ConnectionManager cm = ContextLoader.get();
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
		ConnectionManager cm = ContextLoader.get();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			assertTrue(cm.isClosed());
			cm.openConnection();
			assertFalse(cm.isClosed());
			stmt = cm.prepare(Queries.query, null, null);
			assertTrue(stmt instanceof Statement);
			rs = cm.executeQuery(stmt, Queries.query, null, null);
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

}
