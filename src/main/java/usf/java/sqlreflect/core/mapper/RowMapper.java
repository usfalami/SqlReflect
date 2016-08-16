package usf.java.sqlreflect.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.field.Row;
import usf.java.sqlreflect.core.stream.StreamWriter;

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
			writer.startObject("item");
			for(String str : columnNames)
				writer.writeString(str, ""+bean.get(str));
			writer.endObject();
		}catch(Exception e) {
			e.printStackTrace();
			throw new AdapterException(e);
		}
	}
	
}
