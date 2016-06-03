package usf.java.reflect.parser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.field.Schema;

public class SchemaParser implements Parser {

	@Override
	public void run(ParserAdapter adapter, String schema) throws SQLException {
		//String schema = adapter.getSchema();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet rs = null;
			try {
				//rs = dm.getSchemas(null, schema);
				rs = dm.getSchemas();
				while(rs.next()) adapter.performSchema(new Schema(rs.getString("TABLE_SCHEM")));
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			finally {
				if(rs != null) rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			adapter.getConnectionManager().closeConnection(cnx);
		}
	}
	
	@Override
	public void run(ParserAdapter adapter) throws SQLException {
		run(adapter, null);
	}	
}
