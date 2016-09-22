package usf.java.sqlreflect.reflect.executor;

import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.bender.Binder;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;

public abstract class AbstractExecutor<T> extends AbstractReflector<TransactionManager> implements Executor<T> {
	
	public AbstractExecutor(TransactionManager tm) {
		super(tm);
	}
	
	@Override
	public List<T> run() throws Exception {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter);
		return adapter.getList();
	}
	
	

	public abstract <P> void run(Adapter<Integer> adapter, Object args, Binder<P> binder) throws Exception;
	
}
