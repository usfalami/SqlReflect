package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.Queries.Helper;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.binder.BinderProxy;
import usf.java.sqlreflect.binder.EntryMultiBinder;
import usf.java.sqlreflect.binder.ParameterBinder;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.entry.Entry;

public class ConnectionManagerImplTest {
	
	@Test
	public void testGetServer() {
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
	public void testIsClose() throws SQLException {
		assertTrue(getConnectionManager().isClosed());
	}

	@Test(expected=SQLException.class)
	public void testPrepare() throws SQLException {
		getConnectionManager().prepare("SELECT 1", null, null);
	}
	
	@Test(expected=SQLException.class)
	public void testGetConnection() throws SQLException {
		getConnectionManager().getConnection();
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
		Binder<Serializable[]> binder = new Binder<Serializable[]>() {
			@Override
			public void post(Statement stmt, Serializable[] item) throws SQLException {}
			@Override
			public void bind(Statement stmt, Serializable[] item) throws SQLException {
				((PreparedStatement)stmt).setString(1, item[0].toString());
			}
		};
		Serializable[] args = new Serializable[]{Queries.select_country_result_1[0].toString()};
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Connection c = openConnectionTest(cm);
			stmt = cm.prepare(setlectQuery, args, binder);
			statementTest(stmt, Statement.class);
			rs = cm.executeQuery(stmt, setlectQuery, args, binder);
			resultTest(rs, Queries.select_country_result_1); 
			closeResultSetTest(cm, rs);
			closeStatementTest(cm, stmt);
			closeConnectionTest(cm, c);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSelect3() {
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

	@Test
	public void testSelect4() {
		String selectQuery = Helper.build(Queries.select_country_query);
		String methodName = "findCityByCountryAndDistrict";
		ConnectionManager cm = getConnectionManager();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			BinderProxy<Entry> binder = BinderProxy.get(EntryMultiBinder.class, methodName);
			Connection c = openConnectionTest(cm);
			stmt = cm.prepare(selectQuery, Queries.select_country_bind_Params_3, binder);
			assertTrue(stmt instanceof PreparedStatement);
			rs = cm.executeQuery(stmt, selectQuery, Queries.select_country_bind_Params_3, binder);
			resultTest(rs, Queries.select_country_result_1);
			closeResultSetTest(cm, rs);
			closeStatementTest(cm, stmt);
			closeConnectionTest(cm, c);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(expected=SQLException.class)
	public void testSelect6() throws Throwable {
		String methodName = "notExistingMethod";
		ConnectionManager cm = ContextLoader.getConnectionManager();
		Connection c = null;
		Statement stmt = null;
		try {
			BinderProxy<Entry> binder = BinderProxy.get(EntryMultiBinder.class, methodName);
			c = openConnectionTest(cm);
			stmt = cm.prepare(Queries.query1, Queries.select_country_bind_Params_3, binder);
		}finally{
			assertNull(stmt);
			try {
				closeConnectionTest(cm, c);
			} catch (SQLException e) {}
		}
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
		ContextLoader.forceCloseConnectionManager();
	}
	
}
