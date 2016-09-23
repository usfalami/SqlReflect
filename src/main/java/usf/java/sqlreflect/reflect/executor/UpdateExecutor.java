package usf.java.sqlreflect.reflect.executor;

import java.sql.Statement;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.Runnable;

public class UpdateExecutor extends AbstractExecutor<Integer> {

	private Runnable query;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	public UpdateExecutor(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	public UpdateExecutor set(String sql) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		return this;
	}

	@Override
	protected <P> void runExec(Adapter<Integer> adapter, Object obj, Binder<P> binder) throws Exception {
		P args = (obj == null) ? null : (P) obj;
		Statement stmt = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_STATEMENT);
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
	

	public <P> void run(Adapter<Integer> adapter, P argsList, Binder<P> binder) throws Exception {
		super.prepare(adapter, argsList, binder);
	}
	public <P> List<Integer> run(P argsList, Binder<P> binder) throws Exception {
		ListAdapter<Integer> adapter = new ListAdapter<Integer>();
		this.prepare(adapter, argsList, binder);
		return adapter.getList();
	}
	
}
