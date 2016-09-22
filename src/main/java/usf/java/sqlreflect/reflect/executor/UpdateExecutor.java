package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.bender.Binder;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class UpdateExecutor<P> extends AbstractExecutor<Integer> {

	private Binder<P> binder;
	private Runnable query;
	private P args;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	
	public UpdateExecutor<P> set(String sql) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		return this;
	}
	
	public UpdateExecutor<P> set(String sql, P args, Binder<P> binder) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		this.binder = binder;
		return this;
	}
	
	@Override
	protected void run(TransactionManager tm, Adapter<Integer> adapter, TimePerform tp) throws Exception {
		Statement stmt = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_STATEMENT);
			stmt = tm.buildStatement(query, args, binder);
			action.end();

			action = tp.startAction(Constants.ACTION_EXECUTION);
			int rows = tm.executeUpdate(stmt, query, args, binder);
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
