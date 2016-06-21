package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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

}
