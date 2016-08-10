package usf.java.sql.core.reflect.updater;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.connection.transaction.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;

public class UpdateExecutor extends AbstractExecutor {
	
	private Query query;
	private Arguments args;

	public UpdateExecutor(ConnectionManager cm) {
		super(cm);
	}
	
	public UpdateExecutor set(String sql, Serializable... args) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = new Arguments(args);
		return this;
	}
	
	@Override
	protected void run(TransactionManager tm, UpdaterAdapter adapter) throws SQLException, AdapterException {
		adapter.start();
		Statement stmt = null;
		try {
			stmt = tm.buildStatement(query, args);
			int count = tm.executeUpdate(stmt, query);
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
