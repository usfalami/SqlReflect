package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Transaction;

public class TransactionExecutor extends AbstractExecutor<Void> {
	
	public TransactionExecutor(TransactionManager cm) {
		super(cm);
	}
	public TransactionExecutor(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected <P> void runExec(Adapter<Void> adapter, Object obj, Binder<P> binder) throws Exception {
		if(obj == null) throw new SQLException("Transaction is null"); //TODO : update msg
		Transaction transaction = (Transaction)obj;
		ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
		transaction.execute(new ReflectorFactory(getConnectionManager(), getTimePerform()));
		action.end();
	}
	
	public <P> void run(Adapter<Void> adapter, Transaction transaction) throws Exception {
		super.prepare(adapter, transaction, null);
	}
	public <P> List<Void> run(Transaction transaction) throws Exception {
		ListAdapter<Void> adapter = new ListAdapter<Void>();
		this.run(adapter, transaction);
		return adapter.getList();
	}
	
}
