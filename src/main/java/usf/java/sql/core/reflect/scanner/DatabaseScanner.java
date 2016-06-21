package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.reflect.Reflector;

public class DatabaseScanner extends Reflector implements Scanner {

	public DatabaseScanner(ConnectionManager cm) {
		super(cm);
	}

	public <D extends Database> void run(HasScanner<D> adapter, String database) throws SQLException {
		Connection cnx = null;
		try {
			cnx = cm.newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			this.run(dm, adapter, database);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}

	protected <D extends Database> void run(DatabaseMetaData dm, HasScanner<D> adapter, String databasePattern) throws SQLException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = databasePattern == null ? dm.getSchemas() : dm.getSchemas(null, databasePattern);
			int row = 0;
			while(rs.next()){
				D database = adapter.getMapper().map(rs, row+1);
				adapter.adapte(database, row++);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(rs);
			adapter.end();
		}
	}

}
