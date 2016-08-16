package usf.java.sqlreflect.core.reflect.executor;

import java.sql.SQLException;

import usf.java.sqlreflect.core.adapter.Transaction;
import usf.java.sqlreflect.core.connection.transaction.TransactionManager;
import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.reflect.ReflectorFactory;

public class TransactionExecutor extends AbstractExecutor<Transaction> {

	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	
	@Override
	protected void run(TransactionManager tm, Transaction adapter) throws SQLException, AdapterException {
		adapter.start();
		adapter.execute(new ReflectorFactory(tm));
		adapter.end();
	}
	
}
