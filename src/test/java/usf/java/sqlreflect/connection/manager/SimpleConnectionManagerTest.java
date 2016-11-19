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

public class SimpleConnectionManagerTest {
	
	@Test
	public void testServer() {
		assertNotNull(getConnectionManager().getServer());
	}

	@Test
	public void testOpenCloseConnection() {
		ConnectionManager cm = getConnectionManager();
		try {
			Connection c = openConnectionTest(cm);
			closeConnectionTest(cm ,c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			cm.close();
		}
	}
	
	@Test
	public void testExecStatment() {
		ConnectionManager cm = getConnectionManager();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection c = openConnectionTest(cm);
			stmt = cm.prepare(Queries.query, null, null);
			assertTrue(stmt instanceof Statement);
			rs = cm.executeQuery(stmt, Queries.query, null, null);
			assertTrue(rs.next());
			assertEquals(rs.getInt(1), 1);
			closeResultSetTest(cm, rs);
			closeStatementTest(cm, stmt);
			closeConnectionTest(cm, c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			cm.close(rs);
			cm.close(stmt);
			cm.close();
		}
	}
	
	@Test(expected=SQLException.class)
	public void getConnection() throws SQLException {
		getConnectionManager().getConnection();
	}
	
	protected Connection openConnectionTest(ConnectionManager cm) throws SQLException {
		assertNotNull(cm);
		assertTrue(cm.isClosed());
		Connection c = cm.openConnection();
		assertNotNull(c);
		assertFalse(cm.isClosed());
		assertFalse(c.isClosed());
		assertEquals(c, cm.getConnection());
		return c;
	}
	protected Connection closeConnectionTest(ConnectionManager cm, Connection c) throws SQLException {
		assertEquals(cm.getConnection(), c);
		assertFalse(c.isClosed());
		assertFalse(cm.isClosed());
		cm.close();
		assertTrue(c.isClosed());
		assertTrue(cm.isClosed());
		return c;
	}
	protected void closeStatementTest(ConnectionManager cm, Statement stmt) throws SQLException{
		assertFalse(cm.isClosed());
		cm.close(stmt);
		assertTrue(stmt.isClosed());
		assertFalse(cm.isClosed());
	}
	protected void closeResultSetTest(ConnectionManager cm, ResultSet rs) throws SQLException{
		assertFalse(cm.isClosed());
		cm.close(rs);
		assertTrue(rs.isClosed());
		assertFalse(cm.isClosed());
	}

	protected ConnectionManager getConnectionManager(){
		return ContextLoader.getConnectionManager();
	}
	
}
