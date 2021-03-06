package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.Queries.Helper;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.binder.ParameterBinder;
import usf.java.sqlreflect.sql.Parameter;

public class TransactionManagerImplTest extends ConnectionManagerImplTest {

	@Test
	public void testStartEndTransaction() throws SQLException {
		TransactionManager tm = getConnectionManager();
		try{
			Connection c = openConnectionTest(tm);
			startTransactionTest(tm, c);
			endTransactionTest(tm, c);
			closeConnectionTest(tm, c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test(expected=SQLException.class)
	public void testStartTransaction() throws SQLException {
		getConnectionManager().startTransaction();
	}
	
	@Test(expected=SQLException.class)
	public void testEndTransaction() throws SQLException {
		getConnectionManager().endTransaction();
	}
	
	@Test(expected=SQLException.class)
	public void testCommit() throws SQLException {
		getConnectionManager().commit();
	}
	
	@Test(expected=SQLException.class)
	public void testBuildBatch1() throws SQLException {
		getConnectionManager().buildBatch();
	}
	@Test(expected=SQLException.class)
	public void testBuildBatch2() throws SQLException {
		getConnectionManager().buildBatch("Query 1", "Query 2");
	}
	@Test(expected=SQLException.class)
	public void testBuildBatch3() throws SQLException {
		Collection<String> list = new ArrayList<String>();
		getConnectionManager().buildBatch("Query 1", list, null);
	}
	
	
	@Test
	public void testInsertRollback1() throws SQLException {
		TransactionManager tm = getConnectionManager();
		String inserQuery = Helper.build(Queries.insert_country_query, Queries.insert_country_result);
		String selectQuery = Helper.build(Queries.select_country_query, Queries.insert_country_result[0]);
		try{
			Connection c = openConnectionTest(tm);
			startTransactionTest(tm, c);
			// insert <= XYZ
			Statement stmt = tm.prepare(inserQuery, null, null);
			statementTest(stmt, Statement.class);
			int res = tm.executeUpdate(stmt, inserQuery, null, null);
			closeStatementTest(tm, stmt);
			assertEquals(res, 1);
			//select => XYZ
			stmt = tm.prepare(selectQuery, null, null);
			statementTest(stmt, Statement.class);
			ResultSet rs = tm.executeQuery(stmt, selectQuery, null, null);
			resultTest(rs, Queries.insert_country_result);
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//rollback
			tm.rollback();
			//select => null
			stmt = tm.prepare(selectQuery, null, null);
			statementTest(stmt, Statement.class);
			rs = tm.executeQuery(stmt, selectQuery, null, null);
			assertFalse(rs.next());
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//end
			endTransactionTest(tm, c);
			closeConnectionTest(tm, c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertRollback2() throws SQLException {
		TransactionManager tm = getConnectionManager();
		String inserQuery = Helper.build(Queries.insert_country_query);
		String selectQuery = Helper.build(Queries.select_country_query);
		
		Binder<List<Parameter<?>>> binder = new ParameterBinder();
		try{
			Connection c = openConnectionTest(tm);
			startTransactionTest(tm, c);
			// insert <= XYZ
			Statement stmt = tm.prepare(inserQuery, Queries.insert_country_bind_params, binder);
			statementTest(stmt, PreparedStatement.class);
			int res = tm.executeUpdate(stmt, inserQuery, Queries.insert_country_bind_params, binder);
			closeStatementTest(tm, stmt);
			assertEquals(res, 1);
			//select => XYZ
			stmt = tm.prepare(selectQuery, Queries.select_country_bind_Params_2, binder);
			statementTest(stmt, PreparedStatement.class);
			ResultSet rs = tm.executeQuery(stmt, selectQuery, Queries.select_country_bind_Params_2, binder);
			resultTest(rs, Queries.insert_country_result);
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//rollback
			tm.rollback();
			//select => null
			stmt = tm.prepare(selectQuery, Queries.select_country_bind_Params_2, binder);
			statementTest(stmt, PreparedStatement.class);
			rs = tm.executeQuery(stmt, selectQuery, Queries.select_country_bind_Params_2, binder);
			assertFalse(rs.next());
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//end
			endTransactionTest(tm, c);
			closeConnectionTest(tm, c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void startTransactionTest(TransactionManager tm, Connection c) throws SQLException {
		assertFalse(tm.isTransacting());
		assertTrue(c.getAutoCommit());
		tm.startTransaction();
		assertTrue(tm.isTransacting());
		assertFalse(c.getAutoCommit());
		assertEquals(tm.getConnection(), c);
	}
	protected void endTransactionTest(TransactionManager tm, Connection c) throws SQLException {
		assertTrue(tm.isTransacting());
		assertFalse(c.getAutoCommit());
		tm.endTransaction();
		assertFalse(tm.isTransacting());
		assertTrue(c.getAutoCommit());
		assertEquals(tm.getConnection(), c);
	}
	
	@Override
	protected TransactionManager getConnectionManager() {
		return ContextLoader.getTransactionManager();
	}
	
	@AfterClass
	public static void afterTest(){
		ContextLoader.forceCloseTransactionManager();
	}

}
