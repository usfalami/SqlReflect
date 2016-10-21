package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;

public abstract class AbstractExecutor<R> extends AbstractReflector<TransactionManager, R> implements Executor {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	public AbstractExecutor(TransactionManager tm, ActionTimer at) {
		super(tm, at);
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		TransactionManager tm = getConnectionManager();
		if(tm.isTransacting()){
			runExecutor(adapter, at);
		}
		else {
			tm.startTransaction();
			try {
				runExecutor(adapter, at);
			} catch (Exception e) {
				tm.rollback();
				throw e;
			}
			finally{
				tm.endTransaction();
			}
		}
	}
	
	protected abstract void runExecutor(Adapter<R> adapter, ActionTimer at) throws Exception;
	
}
