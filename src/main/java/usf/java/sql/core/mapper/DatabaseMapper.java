package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Database;

public class DatabaseMapper implements Mapper<Database> {

	@Override
	public Database map(ResultSet rs, int row) throws SQLException {
		return new Database(rs.getString("TABLE_SCHEM"));
	}

}
