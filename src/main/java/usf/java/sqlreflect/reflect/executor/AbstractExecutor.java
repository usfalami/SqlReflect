package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;

public abstract class AbstractExecutor<T> extends AbstractReflector<TransactionManager> implements Executor<T> {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}

}
