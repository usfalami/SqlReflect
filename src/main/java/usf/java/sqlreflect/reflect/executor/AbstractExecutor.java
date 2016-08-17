package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.Reflector;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractExecutor<T extends Adapter<?>> extends AbstractReflector implements Reflector<T> {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	
	@Override
	public final void run(T adapter) throws Exception {
		TimePerform tp = new TimePerform().start();
		try {
			adapter.start();
			TransactionManager tm = (TransactionManager) getConnectionManager();
			if(tm.isTransactionOpned())
				run(tm, adapter, tp);
			else {
				try {
					
					tp.cnxStart();
					tm.startTransaction();
					tp.cnxEnd();
					
					run(tm, adapter, tp);
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
			tp.end();
			adapter.end(tp);
		}
	}
	
	protected abstract void run(TransactionManager tm, T adapter, TimePerform tp) throws Exception;
	
}
