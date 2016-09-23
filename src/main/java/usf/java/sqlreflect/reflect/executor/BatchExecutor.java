package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;
import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class BatchExecutor<P> extends AbstractExecutor<Integer> {

	private Runnable[] queries;
	private Collection<P> argsList; 
	private Binder<P> binder;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}

	public BatchExecutor<P> set(String... sql) {
		queries = new Runnable[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	
	public BatchExecutor<P> set(String sql, List<P> argsList, Binder<P> binder) {
		queries = new Runnable[]{getConnectionManager().getSqlParser().parseSQL(sql)};
		this.argsList = argsList;
		this.binder = binder;
		return this;
	}
	
	protected void run(Adapter<Integer> adapter, TimePerform tp) throws Exception {
		Statement stmt = null; //TODO : Check query
		try {
			
			TransactionManager tm = getConnectionManager();

			ActionPerform action = tp.startAction(Constants.ACTION_STATEMENT);
			stmt = queries.length > 1 || argsList == null || argsList.isEmpty() ? tm.buildBatch(queries) : tm.buildBatch(queries[0], argsList, binder);
			action.end();
			
			action = tp.startAction(Constants.ACTION_EXECUTION);
			int[] rows = stmt.executeBatch();
			action.end();
			
			action = tp.startAction(Constants.ACTION_ADAPT);
			for(int i=0; i<rows.length; i++)
				adapter.adapte(rows[i], i+1);
			action.end();
			
			tp.setRowCount(Utils.sum(rows));
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(stmt);
		}
	}
}
