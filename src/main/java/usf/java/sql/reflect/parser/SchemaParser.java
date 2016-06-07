package usf.java.sql.reflect.parser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.field.Schema;
import usf.java.sql.reflect.parser.adapter.ParserAdapter;

public class SchemaParser implements Parser {

	@Override
	public void run(ParserAdapter adapter, String schema) throws SQLException {
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			ResultSet rs = null;
			try {
				rs = dm.getSchemas(); //rs = dm.getSchemas(null, schema); thorw exception
				while(rs.next()) adapter.performSchema(new Schema(rs.getString("TABLE_SCHEM")));
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
	}
	
	@Override
	public void run(ParserAdapter adapter) throws SQLException {
		run(adapter, null);
	}	
}
