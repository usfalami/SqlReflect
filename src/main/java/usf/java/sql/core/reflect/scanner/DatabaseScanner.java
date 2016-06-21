package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Database;

public class DatabaseScanner implements Scanner {

	public <T extends Database> void run(HasScanner<T> adapter, String database) throws SQLException {
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			this.run(dm, adapter, database);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(cnx);
		}
	}

	protected <T extends Database> void run(DatabaseMetaData dm, HasScanner<T> adapter, String databasePattern) throws SQLException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = databasePattern == null ? dm.getSchemas() : dm.getSchemas(null, databasePattern);
			int row = 0;
			while(rs.next()){
				T database = adapter.getMapper().map(rs, row+1);
				adapter.adapte(database, row++);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(rs);
			adapter.end();
		}
	}

}
