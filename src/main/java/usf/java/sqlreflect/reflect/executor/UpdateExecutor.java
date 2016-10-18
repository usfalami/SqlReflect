package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class UpdateExecutor<A> extends AbstractExecutor<Integer> {

	private Runnable query;
	private Binder<A> binder;
	private A args;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	public UpdateExecutor(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected void runExec(Adapter<Integer> adapter) throws Exception {
		Statement stmt = null;
		try {

			ActionTimer action = getTimePerform().startAction(Constants.ACTION_STATEMENT);
			stmt = getConnectionManager().buildStatement(query, args, binder);
			action.end();

			action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			int rows = getConnectionManager().executeUpdate(stmt, query, args, binder);
			action.end();

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			adapter.adapte(rows, 1);
			action.end();
			
			getTimePerform().setRowCount(rows);
			
		}finally {
			getConnectionManager().close(stmt);
		}
	}

	public UpdateExecutor<A> set(String sql, A args, Binder<A> binder) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		this.binder = binder;
		return this;
	}
	public UpdateExecutor<A> set(String sql) {
		return this.set(sql, null, null);
	}

}
