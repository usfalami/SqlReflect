package usf.java.sqlreflect.reflect.executor;

import java.io.Serializable;
import java.sql.Statement;

import usf.java.sqlreflect.adapter.ExecutorAdapter;
import usf.java.sqlreflect.connection.transaction.TransactionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;

public class UpdateExecutor extends AbstractExecutor<ExecutorAdapter> {
	
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
	protected void run(TransactionManager tm, ExecutorAdapter adapter) throws Exception {
		Statement stmt = null;
		try {
			adapter.start();
			stmt = tm.buildStatement(tm.getConnection(), query, args);
			int count = tm.executeUpdate(stmt, query);
			adapter.adapte(count);
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(stmt);
			adapter.end();
		}
	}

}
