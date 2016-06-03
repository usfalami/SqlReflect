package usf.java.reflect.parser;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.adapter.parser.ParserAdapter;
import usf.java.field.Schema;
import usf.java.reflect.AbstractReflect;

public class SchemaParser<T extends ParserAdapter> extends AbstractReflect<T> {

	public void list(String schema) throws SQLException {
		Connection cnx = null;
		try {
			cnx = rf.newConnection();
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
			rf.CloseConnection(cnx);
		}
	}
}
