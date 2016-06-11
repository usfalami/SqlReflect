package usf.java.sql.reflect.core.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.db.field.Database;

public class DatabaseScanner implements Scanner<Scanner.HasDatabaseScanner> {

	public void run(Scanner.HasDatabaseScanner adapter, String database) throws SQLException {
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet rs = null;
			try {
				rs = database == null ? dm.getSchemas() : dm.getSchemas(null, database);
				while(rs.next()) adapter.adapte(new Database(rs.getString("TABLE_SCHEM")));
			}
			catch(SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				if(rs != null) rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().closeConnection(cnx);
		}
		adapter.finish();
	}
	
}
