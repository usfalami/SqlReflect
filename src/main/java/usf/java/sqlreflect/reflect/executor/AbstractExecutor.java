package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractExecutor<R> extends AbstractReflector<TransactionManager, R> implements Executor {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	public AbstractExecutor(TransactionManager tm, TimePerform tp) {
		super(tm, tp);
	}
	
	@Override
	public void run(Adapter<R> adapter) throws Exception {
		ActionTimer total = getTimePerform().startAction(getClass().getSimpleName());
		try {
			adapter.start();
			TransactionManager tm = getConnectionManager();
			adapter.prepare(null);
			if(tm.isTransacting()){
				runExec(adapter);
			}
			else {
				try {

					ActionTimer action = getTimePerform().startAction(Constants.ACTION_CONNECTION);
					tm.startTransaction();
					action.end();
					
					runExec(adapter);
					tm.endTransaction();
				} catch (Exception e) {
					tm.rollback();
					throw e;
				}
				finally {
					tm.close();
				}
			}
		}finally{
			total.end();
			adapter.end(getTimePerform());
		}
	}
	
	protected abstract void runExec(Adapter<R> adapter) throws Exception;
	
}
