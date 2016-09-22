package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.bender.Binder;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.ReflectorUtils;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class BatchExecutor<P> extends AbstractExecutor<Integer> {

	private Binder<P> binder;
	private Runnable[] queries;
	private Collection<P> argsList;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}

	public BatchExecutor<P> set(String... sql) {
		queries = new Runnable[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	public BatchExecutor<P> set(String sql, Collection<P> argsList, Binder<P> binder) {
		queries = new Runnable[]{getConnectionManager().getSqlParser().parseSQL(sql)};
		this.argsList = argsList;
		this.binder = binder;
		return this;
	}

	@Override
	protected void run(TransactionManager tm, Adapter<Integer> adapter, TimePerform tp) throws Exception {
		Statement stmt = null; //TODO : Check query
		try {

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
			
			tp.setRowCount(ReflectorUtils.sum(rows));
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(stmt);
		}
	}
	
}
