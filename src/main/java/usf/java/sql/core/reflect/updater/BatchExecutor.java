package usf.java.sql.core.reflect.updater;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.transaction.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;

public class BatchExecutor extends AbstractExecutor {
	
	private Query[] queries;
	private Arguments[] args;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}

	public BatchExecutor set(String... sql) {
		queries = new Query[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	public BatchExecutor set(String sql, Arguments... args) {
		queries = new Query[1];
		queries[0] = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		return this;
	}

	@Override
	public void run(TransactionManager tm, UpdaterAdapter adapter) throws SQLException, AdapterException {
		adapter.start();
		Statement stmt = null;
		try {
			stmt = queries.length > 1 ? tm.buildBatch(queries) : tm.buildBatch(queries[0], args);
			int[] count = stmt.executeBatch();
			adapter.adapte(count);
		} catch (SQLException e) {
			throw e;
		}
		finally {
			getConnectionManager().close(stmt);
			adapter.end();
		}
	}
	
}
