package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public abstract class AbstractDataScanner<T> extends AbstractReflector<ConnectionManager> implements Scanner<T> {
	
	private Runnable runnable;

	public AbstractDataScanner(ConnectionManager cm) {
		super(cm);
	}
	public AbstractDataScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	public AbstractDataScanner<T> set(String sql) {
		this.runnable = getConnectionManager().getSqlParser().parseSQL(sql);
		return this;
	}
	
	@Override
	public List<T> run() throws Exception {
		return this.run(null, null);
	}
	public <P> List<T> run(P args, Binder<P> binder) throws Exception {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter, args, binder);
		return adapter.getList();
	}
	
	@Override
	public void run(Adapter<T> adapter) throws Exception {
		this.run(adapter, null, null);
	}
	public final <P> void run(Adapter<T> adapter, P args, Binder<P> binder) throws Exception {
		ActionPerform total = getTimePerform().startAction(Constants.ACTION_TOTAL);
		try {
			adapter.start();

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_CONNECTION);
			getConnectionManager().openConnection();
			action.end();
			
			Statement stmt = null;
			try {

				action = getTimePerform().startAction(Constants.ACTION_STATEMENT);
				stmt = getConnectionManager().buildStatement(runnable, args, binder);
				action.end();
				
				ResultSet rs = null;

				try {
				
					action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
					rs = getConnectionManager().executeQuery(stmt, runnable.asQuery(), args, binder);
					action.end();
					
					runScan(rs, adapter);
				
				}finally {
					getConnectionManager().close(rs);
				}
			}finally {
				getConnectionManager().close(stmt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close();
			total.end();
			adapter.end(getTimePerform());
		}
	}

	protected abstract void runScan(ResultSet rs, Adapter<T> adapter) throws Exception;

}
