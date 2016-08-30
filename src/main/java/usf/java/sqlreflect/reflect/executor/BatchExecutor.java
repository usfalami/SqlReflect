package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.ReflectorUtils;
import usf.java.sqlreflect.reflect.TimePerform;

public class BatchExecutor extends AbstractExecutor<Integer> {
	
	private Query[] queries;
	private Arguments[] args;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}

	public BatchExecutor set(String... sql) {
		queries = new Query[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	public BatchExecutor set(String sql, Arguments... args) {
		queries = new Query[1];
		queries[0] = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		return this;
	}

	@Override
	protected void run(TransactionManager tm, Adapter<Integer> adapter, TimePerform tp) throws Exception {
		Statement stmt = null;
		try {

			ActionPerform action = tp.startAction(STATEMENT);
			stmt = queries.length > 1 || args == null ? tm.buildBatch(queries) : tm.buildBatch(queries[0], args);
			action.end();
			
			action = tp.startAction(EXECUTION);
			int[] rows = stmt.executeBatch();
			action.end();
			
			action = tp.startAction(ADAPT);
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
