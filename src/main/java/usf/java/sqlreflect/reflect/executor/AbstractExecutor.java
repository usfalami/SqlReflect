package usf.java.sqlreflect.reflect.executor;

import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractExecutor<T> extends AbstractReflector<TransactionManager> implements Executor<T> {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	
	protected <P> void runExec(Adapter<T> adapter, Object obj, Binder<P> binder) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(Constants.ACTION_TOTAL);
		try {
			adapter.start();
			TransactionManager tm = getConnectionManager();
			adapter.prepare(null);
			if(tm.isTransacting())
				runExec(adapter, obj, binder, tp);
			else {
				try {

					ActionPerform action = tp.startAction(Constants.ACTION_CONNECTION);
					tm.startTransaction();
					action.end();
					runExec(adapter, obj, binder, tp);
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
			total.end();
			adapter.end(tp);
		}
	}
	
	@Override
	public void run(Adapter<T> adapter) throws Exception {
		this.runExec(adapter, null, null);
	}
	@Override
	public List<T> run() throws Exception {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.runExec(adapter, null, null);
		return adapter.getList();
	}
	
	//No really optim, but resolve mismatch signature of derrived Class
	protected abstract <P> void runExec(Adapter<T> adapter, Object obj, Binder<P> binder, TimePerform tp) throws Exception;
	

}
