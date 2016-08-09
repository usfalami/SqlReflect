package usf.java.sql.core.reflect.updater;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Arguments;
import usf.java.sql.core.reflect.Reflector;

public class Exec extends Reflector implements Updater {
	
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
	public void run(UpdaterAdapter adapter) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = getConnectionManager().getConnection();
			Statement stmt = null;
			adapter.start();
			try {
				stmt = getConnectionManager().buildStatement(cnx, query, args);
				int count = getConnectionManager().executeUpdate(stmt, query.getSQL());
				adapter.adapte(count);
			} catch (SQLException e) {
				throw e;
			}
			finally {
				getConnectionManager().close(stmt);
				adapter.end();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close(cnx);
		}
	}

}
