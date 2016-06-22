package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;
import usf.java.sql.core.stream.StreamWriter;

public class ColumnMapper implements Mapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		return new Column(
				md.getColumnName(row),
				md.getColumnTypeName(row),
				md.getColumnDisplaySize(row),
				md.getColumnClassName(row));
	}

	@Override
	public void write(StreamWriter writer, Column column) throws Exception {
		writer.startObject("COLUMN", new String[]{"COLUMN_NAME", "TYPE_NAME", "LENGTH", "CLASS"});
		writer.writeString("COLUMN_NAME", column.getName());
		writer.writeString("TYPE_NAME", column.getType());
		writer.writeInt("LENGTH", column.getSize());
		writer.writeString("CLASS", column.getClazz());
		writer.endObject();
	}
	

}
