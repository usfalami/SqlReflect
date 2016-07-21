package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;
import usf.java.sql.core.stream.StreamWriter;

public class TableColumnMapper implements Mapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws SQLException {
		return new Column(
			rs.getString("COLUMN_NAME").toString(),
			rs.getString("TYPE_NAME").toString(),
			rs.getInt("COLUMN_SIZE"),
			null
		);
	}

	@Override
	public void write(StreamWriter writer, Column parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString("COLUMN_NAME", parameter.getName());
		writer.writeString("TYPE_NAME", parameter.getType());
		writer.writeInt("COLUMN_SIZE", parameter.getSize());
		writer.writeString("TYPE_NAME", parameter.getType().toString());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"COLUMN_NAME", "TYPE_NAME", "COLUMN_SIZE", "TYPE_NAME"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}