package usf.java.sqlreflect.connection.manager;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Test;

import usf.java.sqlreflect.ContextLoader;

public class SimpleTransactionManagerTest extends SimpleConnectionManagerTest {
	
	@Override
	protected TransactionManager getConnectionManager() {
		return ContextLoader.getTransactionManager();
	}
	
	@Test
	public void testOpenCloseTransaction() throws SQLException {
		TransactionManager tm = getConnectionManager();
		try{
			assertTrue(tm.isClosed());
			tm.openConnection();
			assertFalse(tm.isClosed());
			Connection c = tm.getConnection();
			assertFalse(c.isClosed());
			assertNotNull(c);
			assertFalse(tm.isTransacting());
			assertTrue(c.getAutoCommit());
			tm.startTransaction();
			assertTrue(tm.isTransacting());
			assertFalse(c.getAutoCommit());
			tm.endTransaction();
			assertFalse(tm.isTransacting());
			assertTrue(c.getAutoCommit());
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			tm.endTransaction();
			tm.close();
		}
	}

}
