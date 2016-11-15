package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;

public abstract class AbstractStatementExecutor<R> extends AbstractExecutor<R> {
	
	public AbstractStatementExecutor(TransactionManager cm) {
		super(cm);
	}
	public AbstractStatementExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected void runExecutor(Adapter<R> adapter, ActionTimer at) throws Exception {

		Statement stmt = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_STATEMENT);
			stmt = runStatement();
			action.end();//ACTION_STATEMENT end

			action = at.startAction(Constants.ACTION_EXECUTION);
			R r = runExecution(stmt);
			action.end();//ACTION_EXECUTION end

			action = at.startAction(Constants.ACTION_ADAPT);
			adapter.prepare(null);
			adapter.adapte(r, 1);
			action.end();//ACTION_ADAPT end
			
		}finally {
			getConnectionManager().close(stmt);
		}
	}
	
	public final R run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		 Iterator<R> it = adapter.getList().iterator();
		return it.hasNext() ? it.next() : null;
	}

	protected abstract Statement runStatement() throws SQLException ;

	protected abstract R runExecution(Statement stmt) throws SQLException ;
}
