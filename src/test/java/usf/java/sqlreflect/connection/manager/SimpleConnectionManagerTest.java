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

import com.mysql.jdbc.PreparedStatement;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.Queries.Helper;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.binder.ParameterBinder;
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
	public void testSelect1() {
		ConnectionManager cm = getConnectionManager();
		String setlectQuery = Helper.build(Queries.select_country_query, Queries.select_country_result_1[0]);
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection c = openConnectionTest(cm);
			stmt = cm.prepare(setlectQuery, null, null);
			statementTest(stmt, Statement.class);
			rs = cm.executeQuery(stmt, setlectQuery, null, null);
			resultTest(rs, Queries.select_country_result_1); 
			closeResultSetTest(cm, rs);
			closeStatementTest(cm, stmt);
			closeConnectionTest(cm, c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelect2() {
		ConnectionManager cm = getConnectionManager();
		String setlectQuery = Helper.build(Queries.select_country_query);
		Binder<List<Parameter<?>>> binder = new ParameterBinder();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection c = openConnectionTest(cm);
			stmt = cm.prepare(setlectQuery, Queries.select_country_bind_Params_1, binder);
			statementTest(stmt, PreparedStatement.class);
			rs = cm.executeQuery(stmt, setlectQuery, Queries.select_country_bind_Params_1, binder);
			resultTest(rs, Queries.select_country_result_1); 
			closeResultSetTest(cm, rs);
			closeStatementTest(cm, stmt);
			closeConnectionTest(cm, c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test(expected=SQLException.class)
	public void testGetConnection() throws SQLException {
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
	
	protected void resultTest(ResultSet rs, Object... data) throws SQLException{
		if(Utils.isEmptyArray(data)) return;
		assertTrue(rs.next());
		for(int i=0; i<data.length; i++)
			assertEquals(rs.getObject(i+1), data[i]);
	}

	protected ConnectionManager getConnectionManager(){
		return ContextLoader.getConnectionManager();
	}
	
	@AfterClass
	public static void afterTest(){
		ContextLoader.closeConnectionManager();
	}
	
}
