package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;

public abstract class AbstractExecutor<T extends Adapter> extends AbstractReflector implements Executor<T> {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	
	@Override
	public final void run(T adapter) throws Exception {
		try {
			adapter.start();
			TransactionManager tm = (TransactionManager) getConnectionManager();
			if(tm.isTransaction())
				run(tm, adapter);
			else {
				try {
					tm.startTransaction();
					run(tm, adapter);
					tm.endTransaction();
				} catch (Exception e) {
					tm.rollback();
					throw e;
				}
				finally {
					tm.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}finally{
			adapter.end();
		}

	}
	
	protected abstract void run(TransactionManager tm, T adapter) throws Exception;
	
}
