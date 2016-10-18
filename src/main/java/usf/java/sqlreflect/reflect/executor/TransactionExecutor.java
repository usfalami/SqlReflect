package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Transaction;

public class TransactionExecutor extends AbstractExecutor<Void> {
	
	private Transaction transaction;
	
	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	public TransactionExecutor(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	@Override
	protected void runExec(Adapter<Void> adapter) throws Exception {
		ActionTimer action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
		transaction.execute(new ReflectorFactory(getConnectionManager(), getTimePerform()));
		action.end();
	}

	public TransactionExecutor set(Transaction transaction) {
		this.transaction = transaction;
		return this;
	}
	
}
