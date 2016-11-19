package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
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
import usf.java.sqlreflect.sql.ParameterFactory;

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
	public void testInsertRollback() throws SQLException {
		TransactionManager tm = getConnectionManager();
		List<Parameter<?>> insertParam = new ArrayList<Parameter<?>>();
		insertParam.add(ParameterFactory.CHAR_WRAPPER("XYZ"));
		insertParam.add(ParameterFactory.CHAR_WRAPPER("MyCountry"));
		insertParam.add(ParameterFactory.INTEGER_WRAPPER(null));
		insertParam.add(ParameterFactory.CHAR_WRAPPER("ZZ"));

		List<Parameter<?>> selectParam = new ArrayList<Parameter<?>>();
		selectParam.add(insertParam.get(0));
		
		Binder<List<Parameter<?>>> binder = new ParameterBinder();
		try{
			Connection c = openConnectionTest(tm);
			openTransactionTest(tm, c);
			// insert
			Statement stmt = tm.prepare(Queries.query4, insertParam, binder);
			statementTest(stmt, Statement.class);
			int res = tm.executeUpdate(stmt, Queries.query4, insertParam, binder);
			closeStatementTest(tm, stmt);
			assertEquals(res, 1);
			//select
			stmt = tm.prepare(Queries.query3, selectParam, binder);
			statementTest(stmt, Statement.class);
			ResultSet rs = tm.executeQuery(stmt, Queries.query3, selectParam, binder);
			assertTrue(rs.next());
			assertEquals(rs.getString(2), insertParam.get(1).getValue());
			closeResultSetTest(tm, rs);
			closeStatementTest(tm, stmt);
			//rollback
			tm.rollback();
			//select
			stmt = tm.prepare(Queries.query3, selectParam, binder);
			statementTest(stmt, Statement.class);
			rs = tm.executeQuery(stmt, Queries.query3, selectParam, binder);
			assertFalse(rs.next());
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
