package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sql.core.reflect.exception.AdapterException;
import usf.java.sql.core.stream.StreamWriter;

public class DefaultBeanMapper implements DynamicMapper<Map<String, Object>> {
	
	private String[] columnNames;
	
	@Override
	public void setColumnNames(String... columnNames) {
		this.columnNames = columnNames;
	}
	@Override
	public String[] getColumnNames() {
		return columnNames;
	}

	@Override
	public Map<String, Object> map(ResultSet rs, int row) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		for(String col : columnNames)
			map.put(col, rs.getObject(col));
		return map;
	}

	@Override
	public void write(StreamWriter writer, Map<String, Object> bean) throws AdapterException {
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
