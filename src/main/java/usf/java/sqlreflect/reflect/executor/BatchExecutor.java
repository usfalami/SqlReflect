package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.Runnable;

public class BatchExecutor<A> extends AbstractExecutor<Integer> {

	private Runnable[] queries;
	private Collection<A> argsList;
	private Binder<A> binder;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}
	public BatchExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}

	@Override
	protected void runExec(Adapter<Integer> adapter, ActionTimer at) throws Exception {
		Statement stmt = null; //TODO : Check query
		try {
			
			TransactionManager tm = getConnectionManager();

			ActionTimer action = at.startAction(Constants.ACTION_STATEMENT);
			stmt = queries.length > 1 || Utils.isEmpty(argsList) ? tm.buildBatch(queries) : tm.buildBatch(queries[0], argsList, binder);
			action.end();
			
			action = at.startAction(Constants.ACTION_EXECUTION);
			int[] rows = stmt.executeBatch();
			action.end();
			
			action = at.startAction(Constants.ACTION_ADAPT);
			for(int i=0; i<rows.length; i++)
				adapter.adapte(rows[i], i+1);
			action.end();
			
		}finally {
			getConnectionManager().close(stmt);
		}
	}

	public BatchExecutor<A> set(String sql, Collection<A> argsList, Binder<A> binder) throws Exception {
		this.queries = new Runnable[]{getConnectionManager().getSqlParser().parseSQL(sql)};
		this.argsList = argsList;
		this.binder = binder;
		return this;
	}
	public BatchExecutor<A> set(String... sql) throws Exception {
		queries = new Runnable[queries.length];
		for(int i=0; i<queries.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	
}
