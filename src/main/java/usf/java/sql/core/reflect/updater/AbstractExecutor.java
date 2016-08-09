package usf.java.sql.core.reflect.updater;

import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.connection.transcation.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Reflector;

public abstract class AbstractExecutor extends Reflector implements Updater {

	public AbstractExecutor(ConnectionManager cm) {
		super(cm);
	}

	@Override
	public void run(UpdaterAdapter adapter) throws SQLException, AdapterException {
		TransactionManager tm = null;
		try {
			tm = getConnectionManager().getTransactionManager();
			tm.startTransaction();
			run(tm, adapter);
			tm.endTransaction();
		} catch (SQLException e) {
			e.printStackTrace();
			tm.rollback();
			throw e;
		}
		finally {
			getConnectionManager().close(tm);
		}
	}
	
	protected abstract void run(TransactionManager tm, UpdaterAdapter adapter) throws SQLException, AdapterException;
	
	
}
