package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class BatchExecutor extends AbstractExecutor<Integer> {

	private Runnable[] queries;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}
	public BatchExecutor(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	public BatchExecutor set(String... sql) {
		queries = new Runnable[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	
	@Override
	protected <P> void runExec(Adapter<Integer> adapter, Object obj, Binder<P> binder) throws Exception {
		Collection<P> argsList = obj == null ? null : (Collection<P>) obj;
		Statement stmt = null; //TODO : Check query
		try {
			
			TransactionManager tm = getConnectionManager();

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_STATEMENT);
			stmt = queries.length > 1 || argsList == null || argsList.isEmpty() ? tm.buildBatch(queries) : tm.buildBatch(queries[0], argsList, binder);
			action.end();
			
			action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			int[] rows = stmt.executeBatch();
			action.end();
			
			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			for(int i=0; i<rows.length; i++)
				adapter.adapte(rows[i], i+1);
			action.end();
			
			getTimePerform().setRowCount(Utils.sum(rows));

		}finally {
			getConnectionManager().close(stmt);
		}
	}

	public <P> void run(Adapter<Integer> adapter, Collection<P> argsList, Binder<P> binder) throws Exception {
		super.prepare(adapter, argsList, binder);
	}
	public <P> List<Integer> run(Collection<P> argsList, Binder<P> binder) throws Exception {
		ListAdapter<Integer> adapter = new ListAdapter<Integer>();
		this.run(adapter, argsList, binder);
		return adapter.getList();
	}
	
	

}
