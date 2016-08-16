package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;

import usf.java.sqlreflect.adapter.Transaction;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.reflect.ReflectorFactory;

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
