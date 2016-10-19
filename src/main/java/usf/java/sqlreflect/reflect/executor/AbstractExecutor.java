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
		adapter.prepare(null);
		if(tm.isTransacting()){
			runExec(adapter, at);
		}
		else {
			try {
				
				tm.startTransaction();
				runExec(adapter, at);
				tm.endTransaction();
				
			} catch (Exception e) {
				tm.rollback();
				throw e;
			}
		}
	}
	
	protected abstract void runExec(Adapter<R> adapter, ActionTimer at) throws Exception;
	
}
