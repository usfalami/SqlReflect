package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
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

			ActionTimer action = at.startAction(Constants.ACTION_BINDING);
			stmt = runPreparation();
			action.end();//ACTION_STATEMENT end

			action = at.startAction(Constants.ACTION_EXECUTION);
			R r = runExecution(stmt);
			action.end();//ACTION_EXECUTION end
			
			action = at.startAction(Constants.ACTION_PREPARATION);
			adapter.prepare(null);
			action.end();

			action = at.startAction(Constants.ACTION_PROCESSING);
			adapter.adapte(r, 1);
			action.end();//ACTION_ADAPT end
			
		}finally {
			getConnectionManager().close(stmt);
		}
	}

	protected abstract Statement runPreparation() throws SQLException ;

	protected abstract R runExecution(Statement stmt) throws SQLException ;
}
