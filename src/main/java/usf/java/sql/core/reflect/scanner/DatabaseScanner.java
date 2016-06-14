package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.db.field.Database;
import usf.java.sql.core.mapper.BeanMapper;

public class DatabaseScanner implements Scanner<Scanner.HasDatabaseScanner> {

	public void run(Scanner.HasDatabaseScanner adapter, BeanMapper<?extends Database> mapper, String database) throws SQLException {
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet rs = null;
			try {
				rs = database == null ? dm.getSchemas() : dm.getSchemas(null, database);
				int i=1;
				while(rs.next()) adapter.adapte(mapper.map(rs, i++));
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
