package usf.java.sqlreflect.reflect.executor;

import java.util.Iterator;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
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
				ActionTimer action = at.startAction(Constants.ACTION_ROLLBACK);
				tm.rollback();
				action.end();
				throw e;
			}
			finally{
				tm.endTransaction();
			}
		}
	}
	
	public final R run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		 Iterator<R> it = adapter.getList().iterator();
		return it.hasNext() ? it.next() : null;
	}
	
	protected abstract void runExecutor(Adapter<R> adapter, ActionTimer at) throws Exception;
	
}
