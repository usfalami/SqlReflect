package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.field.Row;
import usf.java.sqlreflect.stream.StreamWriter;

public class RowMapper implements Mapper<Row> {
	
	protected String[] columnNames;
	
	@Override
	public void setColumnNames(String... columnNames) {
		this.columnNames = columnNames;
	}
	@Override
	public String[] getColumnNames() {
		return columnNames;
	}

	@Override
	public Row map(ResultSet rs, int row) throws SQLException {
		Row map = new Row();
		for(String col : columnNames)
			map.put(col, rs.getObject(col));
		return map;
	}

	@Override
	public void write(StreamWriter writer, Row bean) throws Exception {
		writer.startObject("item");
		for(String str : columnNames)
			writer.writeString(str, ""+bean.get(str));
		writer.endObject();
	}
	
}
