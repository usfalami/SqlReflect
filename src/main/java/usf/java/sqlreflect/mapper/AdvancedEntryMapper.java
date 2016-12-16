package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.filter.MapperFilter;
import usf.java.sqlreflect.mapper.filter.ResultConverter;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.writer.TypeWriter;

public class AdvancedEntryMapper<T extends Entry> extends EntryMapper<T> implements HasFilters {

	private Map<String, MapperFilter> mapperFilters;

	public AdvancedEntryMapper(Class<T> clazz, String... columnNames) {
		super(clazz, columnNames);
		mapperFilters = new LinkedHashMap<String, MapperFilter>();
	}

	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		super.prepare(rs, type);
		String[] columnNames = super.getSelectedColumns(); 
		for(String column : columnNames){
			if(Utils.isNull(mapperFilters.get(column)))
				mapperFilters.put(column, new MapperFilter(column));
		}
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = getMappedClass().newInstance();
		Collection<MapperFilter> filters = mapperFilters.values();
		for(MapperFilter filter : filters) {
			Object value = rs.getObject(filter.getColumnName());
			item.set(filter.getPropertyName(), filter.getValueConverter().convert(value));
		}
		return item;
	}
	
	@Override
	public String[] getSelectedColumns() {
		Collection<MapperFilter> filters = mapperFilters.values();
		String[] columns = new String[filters.size()]; int i=0;
		for(Iterator<MapperFilter> it = filters.iterator(); it.hasNext(); i++)
			columns[i] = it.next().getPropertyName();
		return columns;
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
	
	@Override
	public Map<String, TypeWriter> getTypes() throws SQLException {
		return Utils.columnTypes(getResultSet().getMetaData(), mapperFilters);
	}
	
}
