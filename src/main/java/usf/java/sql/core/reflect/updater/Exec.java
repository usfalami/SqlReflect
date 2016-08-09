package usf.java.sql.core.reflect.updater;

import java.io.Serializable;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.connection.transcation.TransactionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;

public class Exec extends AbstractExecutor {
	
	private Query query;
	private Arguments args;

	public Exec(ConnectionManager cm) {
		super(cm);
	}
	
	public Exec set(String sql, Serializable... args) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = new Arguments(args);
		return this;
	}
	@Override
	protected void run(TransactionManager tm, UpdaterAdapter adapter) throws SQLException, AdapterException {
		adapter.start();
		Statement stmt = null;
		try {
			int count = tm.executeUpdate(query, args);
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
