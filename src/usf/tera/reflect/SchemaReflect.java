package usf.tera.reflect;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.reflect.field.Schema;

public class SchemaReflect extends Reflect {

	@Override
	protected void find(DatabaseMetaData dm, String name) throws SQLException {
		ResultSet rs  = dm.getSchemas();
		try {
			while(rs.next()) {
				adapter.performSchema(new Schema(rs.getString("TABLE_SCHEM")));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) rs.close();
		}
	}
	
}
