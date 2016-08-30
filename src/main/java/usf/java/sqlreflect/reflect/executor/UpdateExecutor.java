package usf.java.sqlreflect.reflect.executor;

import java.io.Serializable;
import java.sql.Statement;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public class UpdateExecutor extends AbstractExecutor<Integer> {
	
	private Query query;
	private Arguments args;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	
	public UpdateExecutor set(String sql, Serializable... args) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = new Arguments(args);
		return this;
	}
	
	@Override
	protected void run(TransactionManager tm, Adapter<Integer> adapter, TimePerform tp) throws Exception {
		Statement stmt = null;
		try {

			
			ActionPerform action = tp.startAction(STATEMENT);
			stmt = tm.buildStatement(tm.getConnection(), query, args);
			action.end();

			action = tp.startAction(EXECUTION);
			int rows = tm.executeUpdate(stmt, query);
			action.end();

			action = tp.startAction(ADAPT);
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
