package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import usf.java.sqlreflect.mapper.filter.MapperFilter;
import usf.java.sqlreflect.mapper.filter.ValueConverter;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class AdvancedEntryMapper<T extends Entry> extends EntryMapper<T> implements AdvancedMapper<T> {

	private Map<String, MapperFilter> mapperFilters;

	public AdvancedEntryMapper(Class<T> clazz, String... columnNames) {
		super(clazz, columnNames);
		mapperFilters = new HashMap<String, MapperFilter>();
	}

	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		super.prepare(rs, type);
		for(String column : super.getColumnNames()){
			if(mapperFilters.get(column) == null)
				mapperFilters.put(column, new MapperFilter(column, column));
		}
	}
	
	public T map(ResultSet rs, int row) throws Exception {
		T item = getClazz().newInstance();
		for(java.util.Map.Entry<String, MapperFilter> c : mapperFilters.entrySet()) {
			MapperFilter trans = c.getValue();
			Object value = rs.getObject(c.getKey());
			item.set(trans.getMappedName(), trans.getValueConverter().transformer(value));
		}
		return item;
	}

	@Override
	public void addMapperFilter(String columnName, String mappedName, ValueConverter<?> converter) {
		mapperFilters.put(columnName, new MapperFilter(columnName, mappedName, converter));
	}
	@Override
	public void addMapperFilter(String columnName, String mappedName) {
		mapperFilters.put(columnName, new MapperFilter(columnName, mappedName));
	}
	@Override
	public void addMapperFilter(String columnName, ValueConverter<?> converter) {
		mapperFilters.put(columnName, new MapperFilter(columnName, converter));
	}
	
	@Override
	public String[] getColumnNames() {
		String[] mappedColumn = new String[mapperFilters.size()];
		int i=0;
		for(Iterator<MapperFilter> c = mapperFilters.values().iterator(); c.hasNext(); i++)
			mappedColumn[i] = c.next().getMappedName();
		return mappedColumn;
	}
	
}
