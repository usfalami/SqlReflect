package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;

public class ColumnMapper implements BeanMapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws SQLException {
		return new Column(
				rs.getString("COLUMN_NAME").toString(),
				rs.getString("TYPE_NAME").toString(),
				rs.getInt("LENGTH"),
				rs.getInt("COLUMN_TYPE")
			);
	}

}
