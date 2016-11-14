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

import usf.java.sqlreflect.AbstractTest;
import usf.java.sqlreflect.Queries;

public class SimpleConnectionManagerTest   {

	private Statement stmt = null;
	private ResultSet rs = null;

	@Test
	public void testServer() {
		assertNotNull(AbstractTest.getConnectionManager().getServer());
	}

	@Test
	public void testOpenCloseConnection() {
		try {
			assertTrue(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().openConnection();
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			Connection c = AbstractTest.getConnectionManager().getConnection();
			assertNotNull(c);
			assertFalse(c.isClosed());
			AbstractTest.getConnectionManager().openConnection();
			assertEquals(c, AbstractTest.getConnectionManager().getConnection());
			AbstractTest.getConnectionManager().close();
			assertTrue(AbstractTest.getConnectionManager().isClosed());
			assertTrue(c.isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testExecStatment() {
		try {
			assertTrue(AbstractTest.getConnectionManager().isClosed());
			AbstractTest.getConnectionManager().openConnection();
			assertFalse(AbstractTest.getConnectionManager().isClosed());
			stmt = AbstractTest.getConnectionManager().prepare(Queries.query, null, null);
			assertTrue(stmt instanceof Statement);
			rs = AbstractTest.getConnectionManager().executeQuery(stmt, Queries.query, null, null);
			assertTrue(rs.next());
			assertEquals(rs.getInt(1), 1);
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

}
