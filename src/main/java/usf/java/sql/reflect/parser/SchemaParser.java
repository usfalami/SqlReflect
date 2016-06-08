package usf.java.sql.reflect.parser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.db.field.Database;
import usf.java.sql.reflect.parser.adapter.AbstractParserAdapter.DatabaseParserAdapter;

public class SchemaParser implements Parser<DatabaseParserAdapter> {

	@Override
	public void run(DatabaseParserAdapter adapter, String database) throws SQLException {
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet rs = null;
			try {
				rs = dm.getSchemas(); //rs = dm.getSchemas(null, database); thorw exception
				while(rs.next()) adapter.performDatabase(new Database(rs.getString("TABLE_SCHEM")));
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
	
	@Override
	public void run(DatabaseParserAdapter adapter) throws SQLException {
		run(adapter, null);
	}	
}
