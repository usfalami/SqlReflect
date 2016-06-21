package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;
import usf.java.sql.core.stream.StreamWriter;

public class ColumnMapper implements Mapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws SQLException {
		return new Column(
				rs.getString("COLUMN_NAME").toString(),
				rs.getString("TYPE_NAME").toString(),
				rs.getInt("LENGTH"),
				rs.getInt("COLUMN_TYPE")
			);
	}

	
	@Override
	public void write(StreamWriter writer, Column column) throws Exception {
		writer.startObject("COLUMN_NAME", "TYPE_NAME", "LENGTH", "COLUMN_TYPE");
		writer.writeString("COLUMN_NAME", column.getName());
		writer.writeString("TYPE_NAME", column.getValueType());
		writer.writeInt("LENGTH", column.getSize());
		writer.writeString("COLUMN_TYPE", column.getRole().toString());
		writer.endObject();
	}

}
