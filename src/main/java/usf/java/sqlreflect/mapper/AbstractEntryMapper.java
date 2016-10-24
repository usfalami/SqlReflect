package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import usf.java.sqlreflect.sql.entry.Entry;

public abstract class AbstractEntryMapper<T extends Entry> implements Mapper<T> {

	private Map<String, String> columnMapper;
	private Class<T> clazz;
	
	public AbstractEntryMapper(Class<T> clazz) {
		this.clazz = clazz;
		this.columnMapper = new HashMap<String, String>();
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = clazz.newInstance();
		for(java.util.Map.Entry<String, String> c : columnMapper.entrySet())
			item.set(c.getKey(), rs.getObject(c.getValue()));
		return item;
	}

	@Override
	public String[] getColumnNames() {
		Set<String> set = columnMapper.keySet();
		return set.toArray(new String[set.size()]);
	}
	
	public void setColumnNames(String... columnNames) {
		columnMapper.clear();
		for(String cn : columnNames)
			columnMapper.put(cn, cn);
	}
	
	public Map<String, String> getColumnMapper() {
		return columnMapper;
	}
	
	/**
	 * KEY 		: bean columnName
	 * value 	: SQL fieldName
	 */
	public void setColumnMapper(Map<String, String> columnMapper) {
		this.columnMapper = columnMapper;
	}
}
