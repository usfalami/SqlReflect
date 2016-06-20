package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DefaultBeanMapper implements Mapper<Map<String, Object>> {
	
	private String[] columnNames;
	
	public DefaultBeanMapper(String[] columnNames) {
		this.columnNames = columnNames;
	}

	@Override
	public Map<String, Object> map(ResultSet rs, int row) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		for(String col : columnNames)
			map.put(col, rs.getObject(col));
		return map;
	}

}
