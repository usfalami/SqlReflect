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
import java.util.List;

import org.junit.AfterClass;
import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;
import usf.java.sqlreflect.Queries;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.binder.ParameterBinder;
import usf.java.sqlreflect.sql.Parameter;

public class SimpleTransactionManagerTest extends SimpleConnectionManagerTest {

	@Test
	public void testOpenCloseTransaction() throws SQLException {
		TransactionManager tm = getConnectionManager();
		try{
			Connection c = openConnectionTest(tm);
			openTransactionTest(tm, c);
			closeTransactionTest(tm, c);
			closeConnectionTest(tm, c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertRollback1() throws SQLException {
		TransactionManager tm = getConnectionManager();
		try{
			Connection c = openConnectionTest(tm);
			openTransactionTest(tm, c);
			// insert <= XYZ
			Statement stmt = tm.prepare(Queries.insert_country_query, null, null);
			statementTest(stmt, Statement.class);
			int res = tm.executeUpdate(stmt, Queries.insert_country_query, null, null);
			closeStatementTest(tm, stmt);
			assertEquals(res, 1);
			//select => XYZ
			stmt = tm.prepare(Queries.select_country_query, null, null);
			statementTest(stmt, Statement.class);
			ResultSet rs = tm.executeQuery(stmt, Queries.select_country_query, null, null);
			resultTest(rs, Queries.insert_country_bind_Params);
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//rollback
			tm.rollback();
			//select => null
			stmt = tm.prepare(Queries.select_country_query, null, null);
			statementTest(stmt, Statement.class);
			rs = tm.executeQuery(stmt, Queries.select_country_query, null, null);
			assertFalse(rs.next());
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//end
			closeTransactionTest(tm, c);
			closeConnectionTest(tm, c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInsertRollback2() throws SQLException {
		TransactionManager tm = getConnectionManager();
		
		List<Parameter<?>> selectParams = new ArrayList<Parameter<?>>();
		selectParams.add(Queries.insert_country_bind_Params.get(0));
		
		Binder<List<Parameter<?>>> binder = new ParameterBinder();
		try{
			Connection c = openConnectionTest(tm);
			openTransactionTest(tm, c);
			// insert <= XYZ
			Statement stmt = tm.prepare(Queries.insert_country_bind_query, Queries.insert_country_bind_Params, binder);
			statementTest(stmt, PreparedStatement.class);
			int res = tm.executeUpdate(stmt, Queries.insert_country_bind_query, Queries.insert_country_bind_Params, binder);
			closeStatementTest(tm, stmt);
			assertEquals(res, 1);
			//select => XYZ
			stmt = tm.prepare(Queries.select_country_bind_query, selectParams, binder);
			statementTest(stmt, PreparedStatement.class);
			ResultSet rs = tm.executeQuery(stmt, Queries.select_country_bind_query, selectParams, binder);
			resultTest(rs, Queries.insert_country_bind_Params);
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//rollback
			tm.rollback();
			//select => null
			stmt = tm.prepare(Queries.select_country_bind_query, selectParams, binder);
			statementTest(stmt, PreparedStatement.class);
			rs = tm.executeQuery(stmt, Queries.select_country_bind_query, selectParams, binder);
			assertFalse(rs.next());
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//end
			closeTransactionTest(tm, c);
			closeConnectionTest(tm, c);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected void openTransactionTest(TransactionManager tm, Connection c) throws SQLException {
		assertFalse(tm.isTransacting());
		assertTrue(c.getAutoCommit());
		tm.startTransaction();
		assertTrue(tm.isTransacting());
		assertFalse(c.getAutoCommit());
		assertEquals(tm.getConnection(), c);
	}
	protected void closeTransactionTest(TransactionManager tm, Connection c) throws SQLException {
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
		ContextLoader.closeTransactionManager();
	}

}
