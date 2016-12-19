package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.filter.HasFilters;
import usf.java.sqlreflect.mapper.filter.MapperFilter;
import usf.java.sqlreflect.mapper.filter.ResultConverter;
import usf.java.sqlreflect.reflect.SqlUtils;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class FiltredEntryMapper<T extends Entry> implements Mapper<T>, HasFilters {

	private Class<T> mappedClass;
	private Map<String, MapperFilter> mapperFilters;

	public FiltredEntryMapper(Class<T> clazz, String... selectedColumnNames) {
		this.mappedClass = clazz;
		this.mapperFilters = new LinkedHashMap<String, MapperFilter>();
		if(!Utils.isEmptyArray(selectedColumnNames)){
			for(String key : selectedColumnNames)
				this.mapperFilters.put(key, new MapperFilter(key));
		}
	}

	@Override
	public Collection<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		
		return Utils.isEmptyMap(mapperFilters) ? 
				SqlUtils.allColumnNames(rs, mapperFilters) : SqlUtils.columnNames(rs, mapperFilters);
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = mappedClass.newInstance();
		Collection<MapperFilter> filters = mapperFilters.values();
		for(MapperFilter filter : filters) {
			Object value = rs.getObject(filter.getColumnName());
			item.set(filter.getPropertyName(), filter.getValueConverter().convert(value));
		}
		return item;
	}

	@Override
	public void addFilter(String columnName, String propertyName, ResultConverter<?> converter) {
		mapperFilters.put(columnName, new MapperFilter(columnName, propertyName, converter));
	}
	@Override
	public void addFilter(String columnName, String propertyName) {
		mapperFilters.put(columnName, new MapperFilter(columnName, propertyName));
	}
	@Override
	public void addFilter(String columnName, ResultConverter<?> converter) {
		mapperFilters.put(columnName, new MapperFilter(columnName, converter));
	}
	
}
