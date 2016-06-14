package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Database;
import usf.java.sql.core.mapper.BeanMapper;

public class DatabaseScanner implements Scanner {

	public <T extends Database> void run(HasDatabaseScanner<T> adapter, BeanMapper<T> mapper, String database) throws SQLException {
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet rs = null;
			try {
				rs = database == null ? dm.getSchemas() : dm.getSchemas(null, database);
				int i=1;
				while(rs.next()){
					T db = mapper.map(rs, i++);
					adapter.adapte(db);
				}
			}
			catch(SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				adapter.getConnectionManager().close(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(cnx);
		}
		adapter.finish();
	}
	
}
