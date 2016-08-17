package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.adapter.Transaction;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.TimePerform;

public class TransactionExecutor extends AbstractExecutor<Transaction> {

	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	
	@Override
	protected void run(TransactionManager tm, Transaction adapter, TimePerform tp) throws Exception {
		tp.execStart();
		adapter.execute(new ReflectorFactory(tm));
		tp.execEnd();
	}
	
}
