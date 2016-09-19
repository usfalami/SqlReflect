package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Parameter;
import usf.java.sqlreflect.sql.Runnable;

public class UpdateExecutor extends AbstractExecutor<Integer> {
	
	private Runnable query;
	private Parameter<?>[] args;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	
	public UpdateExecutor set(String sql, Parameter<?>... args) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		return this;
	}
	
	@Override
	protected void run(TransactionManager tm, Adapter<Integer> adapter, TimePerform tp) throws Exception {
		Statement stmt = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_STATEMENT);
			stmt = tm.buildStatement(query, args);
			action.end();

			action = tp.startAction(Constants.ACTION_EXECUTION);
			int rows = tm.executeUpdate(stmt, query);
			action.end();

			action = tp.startAction(Constants.ACTION_ADAPT);
			adapter.adapte(rows, 1);
			action.end();
			
			tp.setRowCount(rows);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(stmt);
		}
	}

}
