package usf.java.sql.core.reflect.executor;

import java.sql.SQLException;

import usf.java.sql.core.adapter.Transaction;
import usf.java.sql.core.connection.transaction.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.ReflectorFactory;

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
