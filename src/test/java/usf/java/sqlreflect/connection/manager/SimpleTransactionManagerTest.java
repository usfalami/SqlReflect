package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.AfterClass;
import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;

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
		try {
			ContextLoader.getTransactionManager().getConnection().close();
		} catch (SQLException e) {}
	}

}
