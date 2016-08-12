package usf.java.sql.core.reflect.updater;

import java.sql.SQLException;

import usf.java.sql.core.connection.transaction.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Reflector;

public abstract class AbstractExecutor extends Reflector implements Updater {

	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}

	@Override
	public final void run(UpdaterAdapter adapter) throws SQLException, AdapterException {
		TransactionManager tm = (TransactionManager) getConnectionManager();
		try {
			tm.startTransaction();
			run(tm, adapter);
			tm.endTransaction();
		} catch (SQLException e) {
			tm.rollback();
			e.printStackTrace();
			throw e;
		}
		finally {
			tm.close();
		}
	}
	
	public abstract void run(TransactionManager tm, UpdaterAdapter adapter) throws SQLException, AdapterException;
	
}
