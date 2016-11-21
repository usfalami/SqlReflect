package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.Parameter;

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
	}
	
	@Test
	public void testExecStatment() {
		ConnectionManager cm = getConnectionManager();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection c = openConnectionTest(cm);
			stmt = cm.prepare(Queries.query, null, null);
			statementTest(stmt, Statement.class);
			rs = cm.executeQuery(stmt, Queries.query, null, null);
			assertTrue(rs.next());
			assertEquals(rs.getInt(1), 1);
			closeResultSetTest(cm, rs);
			closeStatementTest(cm, stmt);
			closeConnectionTest(cm, c);
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	protected <T extends Statement> void statementTest(Statement stmt, Class<T> clazz){
		assertTrue(clazz.isInstance(stmt));
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
	
	protected void resultTest(ResultSet rs, List<Parameter<?>> data) throws SQLException{
		if(Utils.isEmptyCollection(data)) return;
		assertTrue(rs.next());
		for(int i=0; i<data.size(); i++)
			assertEquals(rs.getObject(i+1), data.get(i).getValue());
	}

	protected ConnectionManager getConnectionManager(){
		return ContextLoader.getConnectionManager();
	}
	
	@AfterClass
	public static void afterTest(){
		ContextLoader.closeConnectionManager();
	}
	
}
