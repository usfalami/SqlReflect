package usf.java.sql.core.reflect.updater;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.connection.transcation.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;
import usf.java.sql.core.reflect.Reflector;

public class Batch extends Reflector implements Updater {
	
	private Query[] queries;
	private Arguments[] args;

	public Batch(ConnectionManager cm) {
		super(cm);
	}

	public Batch set(String... sql) {
		queries = new Query[sql.length];
		for(int i=0; i<sql.length; i++)
			queries[i] = getConnectionManager().getSqlParser().parseSQL(sql[i]);
		return this;
	}
	public Batch set(String sql, Arguments[] args) {
		queries = new Query[1];
		queries[0] = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		return this;
	}

	@Override
	public void run(UpdaterAdapter adapter) throws SQLException, AdapterException {
		TransactionManager tm = null;
		try {
			tm = getConnectionManager().getTransactionManager();
			Statement stmt = null;
			adapter.start();
			try {
				
				tm.startTransaction();
				stmt = queries.length > 0 ? tm.buildBatch(queries) : tm.buildBatch(queries[0], args);
				int[] count = stmt.executeBatch();
				adapter.adapte(count);
				tm.endTransaction();

			} catch (SQLException e) {
				throw e;
			}
			finally {
				getConnectionManager().close(stmt);
				adapter.end();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			tm.rollback();
			throw e;
		}
		finally {
			getConnectionManager().close(tm);
		}
	}

}
