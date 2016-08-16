package usf.java.sql.core.reflect.executor;

import java.sql.SQLException;

import usf.java.sql.core.adapter.Adapter;
import usf.java.sql.core.connection.transaction.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.AbstractReflector;

public abstract class AbstractExecutor<T extends Adapter> extends AbstractReflector implements Executor<T> {
	
	private boolean subTransaction;
	
	public AbstractExecutor(TransactionManager tm) {
		this(tm, false);
	}
	
	public AbstractExecutor(TransactionManager tm, boolean subTransaction) {
		super(tm);
		this.subTransaction = subTransaction;
	}
	
	@Override
	public final void run(T adapter) throws SQLException, AdapterException {
		TransactionManager tm = (TransactionManager) getConnectionManager();
		if(subTransaction)
			run(tm, adapter);
		else{
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
	}
	
	protected abstract void run(TransactionManager tm, T adapter) throws SQLException, AdapterException;
	
}
