package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.TimePerform;

public class TransactionExecutor extends AbstractExecutor<Adapter<Void>> {
	
	private Transaction transaction;

	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	
	public TransactionExecutor set(Transaction transaction){
		this.transaction = transaction;
		return this;
	}
	
	@Override
	protected void run(TransactionManager tm, Adapter<Void> adapter, TimePerform tp) throws Exception {
		tp.execStart();
		transaction.execute(new ReflectorFactory(tm));
		tp.execEnd();
	}
	
}
