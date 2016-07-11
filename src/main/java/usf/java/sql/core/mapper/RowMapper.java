package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Row;
import usf.java.sql.core.stream.StreamWriter;

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
	public void write(StreamWriter writer, Row bean) throws AdapterException {
		try{
			writer.startObject("item", columnNames);
			for(String str : columnNames)
				writer.writeString(str, bean.get(str).toString());
			writer.endObject();
		}catch(Exception e) {
			e.printStackTrace();
			throw new AdapterException(e);
		}
	}
	
}