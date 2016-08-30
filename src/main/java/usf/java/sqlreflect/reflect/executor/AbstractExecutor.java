package usf.java.sqlreflect.reflect.executor;

import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractExecutor<T> extends AbstractReflector implements Executor<T> {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	
	@Override
	public final void run(Adapter<T> adapter) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(TOTAL);
		try {
			adapter.start();
			TransactionManager tm = (TransactionManager) getConnectionManager();
			adapter.prepare(null);
			if(tm.isTransactionOpned())
				run(tm, adapter, tp);
			else {
				try {

					ActionPerform action = tp.startAction(CONNECTION);
					tm.startTransaction();
					action.end();
					
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
			total.end();
			adapter.end(tp);
		}
	}
	
	@Override
	public List<T> run() throws Exception {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter);
		return adapter.getList();
	}
	
	protected abstract void run(TransactionManager tm, Adapter<T> adapter, TimePerform tp) throws Exception;
	
}
