package usf.tera.reflect.parser;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.tera.adpter.ParserAdapter;
import usf.tera.field.Schema;

public class SchemaParser<T extends ParserAdapter> extends AbstractParser<T> {

	@Override
	protected void lookup(DatabaseMetaData dm, String name) throws SQLException {
		ResultSet rs = dm.getSchemas();
		try {
			while(rs.next()) adapter.performSchema(new Schema(rs.getString("TABLE_SCHEM")));
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) rs.close();
		}
	}
}
