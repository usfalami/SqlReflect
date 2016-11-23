package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.sql.Transaction;

public class TransactionExecutor extends AbstractExecutor<Void> {
	
	private Transaction transaction;
	
	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	public TransactionExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected void runExecutor(Adapter<Void> adapter, ActionTimer at) throws Exception {
		ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
		transaction.execute(new ReflectorFactory(getConnectionManager(), action));
		action.end(); //ACTION_EXECUTION end
	}
	
	@Override
	protected void validateArgs() {
		super.validateArgs();
		if(transaction == null) throw new IllegalArgumentException("Transaction can't be null");
	}

	public TransactionExecutor set(Transaction transaction) {
		this.transaction = transaction;
		return this;
	}
	
}
