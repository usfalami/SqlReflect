package usf.java.sqlreflect.reflect.executor;

import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Transaction;

public class TransactionExecutor extends AbstractExecutor<Void> {
	
	private Transaction transaction;

	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	
	public TransactionExecutor set(Transaction transaction){
		this.transaction = transaction;
		return this;
	}
	
	protected void run(Adapter<Void> adapter, TimePerform tp) throws Exception {
		ActionPerform action = tp.startAction(Constants.ACTION_TRANSACTION);
		transaction.execute(new ReflectorFactory(getConnectionManager()), tp);
		action.end();
	}
	
	//duplicated code
	@Override
	public void run(Adapter<Void> adapter) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(Constants.ACTION_TOTAL);
		try {
			adapter.start();
			TransactionManager tm = getConnectionManager();
			adapter.prepare(null);
			if(tm.isTransacting())
				run(adapter, tp);
			else {
				try {

					ActionPerform action = tp.startAction(Constants.ACTION_CONNECTION);
					tm.startTransaction();
					action.end();
					run(adapter, tp);
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
	public List<Void> run() throws Exception {
		ListAdapter<Void> adapter = new ListAdapter<Void>();
		this.run(adapter);
		return adapter.getList();
	}
	
}
