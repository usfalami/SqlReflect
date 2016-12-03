package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.filter.MapperFilter;
import usf.java.sqlreflect.mapper.filter.ValueConverter;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class AdvancedEntryMapper<T extends Entry> extends EntryMapper<T> implements HasFilters {

	private Map<String, MapperFilter> mapperFilters;

	public AdvancedEntryMapper(Class<T> clazz, String... columnNames) {
		super(clazz, columnNames);
		mapperFilters = new HashMap<String, MapperFilter>();
	}

	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		super.prepare(rs, type);
		String[] columnNames = getColumnNames(); 
		for(String column : columnNames){
			MapperFilter filter = mapperFilters.get(column);
			if(Utils.isNull(filter))
				mapperFilters.put(column, new MapperFilter(column));
		}
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = getClazz().newInstance();
		for(MapperFilter filter : mapperFilters.values()) {
			Object value = rs.getObject(filter.getMappedName());
			item.set(filter.getColumnName(), filter.getValueConverter().transformer(value));
		}
		return item;
	}

	@Override
	public void addMapperFilter(String columnName, String mappedName, ValueConverter<?> converter) {
		mapperFilters.remove(mappedName);
		mapperFilters.put(columnName, new MapperFilter(columnName, mappedName, converter));
	}
	@Override
	public void addMapperFilter(String columnName, String mappedName) {
		mapperFilters.remove(mappedName);
		mapperFilters.put(columnName, new MapperFilter(columnName, mappedName));
	}
	@Override
	public void addMapperFilter(String columnName, ValueConverter<?> converter) {
		mapperFilters.put(columnName, new MapperFilter(columnName, converter));
	}
	
}
