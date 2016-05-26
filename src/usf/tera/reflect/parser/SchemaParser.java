package usf.tera.reflect.parser;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.field.Schema;
import usf.tera.reflect.AbstractReflect;
import usf.tera.reflect.adpter.ParsingAdapter;

public class SchemaParser<T extends ParsingAdapter> extends AbstractParser<T> {

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
