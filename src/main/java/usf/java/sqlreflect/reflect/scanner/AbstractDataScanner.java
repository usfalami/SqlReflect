package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.Runnable;

public abstract class AbstractDataScanner<A, R> extends AbstractReflector<ConnectionManager, R> implements Scanner {
	
	private Runnable runnable;
	private Binder<A> binder;
	private A args;

	public AbstractDataScanner(ConnectionManager cm) {
		super(cm);
	}
	public AbstractDataScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		Statement stmt = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_STATEMENT);
			stmt = getConnectionManager().buildStatement(runnable, args, binder);
			action.end();
			
			ResultSet rs = null;
			try {
			
				action = at.startAction(Constants.ACTION_EXECUTION);
				rs = getConnectionManager().executeQuery(stmt, runnable.asQuery(), args, binder);
				action.end();
				
				runScan(rs, adapter, at);
			
			}finally {
				getConnectionManager().close(rs);
			}
		}finally {
			getConnectionManager().close(stmt);
		}
	}

	protected abstract void runScan(ResultSet rs, Adapter<R> adapter, ActionTimer at) throws Exception;
	
	public AbstractDataScanner<A, R> set(String sql, A args, Binder<A> binder) {
		this.runnable = getConnectionManager().getSqlParser().parseSQL(sql);
		this.binder = binder;
		this.args = args;
		return this;
	}
	public AbstractDataScanner<A, R> set(String sql) {
		return set(sql, null, null);
	}
}
