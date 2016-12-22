package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.HasFilters;
import usf.java.sqlreflect.mapper.filter.ResultConverter;
import usf.java.sqlreflect.reflect.SqlUtils;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class FiltredMapper<T> implements Mapper<T>, HasFilters {
	
	private Class<T> mappedClass;
	private Map<String, Filter> mapperFilters;
	private PropertyMapper<T> propertyMapper;
	private List<Header> headers;

	public FiltredMapper(Class<T> clazz, PropertyMapper<T> propertyMapper, String... selectedColumnNames) {
		this.mappedClass = clazz;
		this.propertyMapper = propertyMapper;
		this.mapperFilters = new HashMap<String, Filter>();
		if(!Utils.isEmptyArray(selectedColumnNames)){
			for(String columnName : selectedColumnNames)
				mapperFilters.put(columnName, new Filter(columnName));
		}
	}

	@Override
	public List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		headers = Utils.isEmptyMap(mapperFilters) ?
			SqlUtils.allColumnFilters(rs) : SqlUtils.columnFilters(rs, mapperFilters);
		propertyMapper.prepare(headers);
		return headers;
	}

	@Override
	public T map(ResultSet rs, int row) throws Exception {
		T item = getMappedClass().newInstance();
		for(Header header : headers) {
			Object value = rs.getObject(header.getColumnName());
			Filter filter = (Filter) header.get(SqlConstants.COLUMN_FILTER);
			value = filter.getConverter().convert(value);
			propertyMapper.setProperty(item, filter.getPropertyName(), value);
		}
		return item;
	}
	
	@Override
	public void addFilter(String columnName, String propertyName, ResultConverter<?> converter) {
		mapperFilters.put(columnName, 
				new Filter(columnName, propertyName, converter));
	}
	@Override
	public void addFilter(String columnName, String propertyName) {
		mapperFilters.put(columnName, new Filter(columnName, propertyName));
	}
	@Override
	public void addFilter(String columnName, ResultConverter<?> converter) {
		mapperFilters.put(columnName, new Filter(columnName, converter));
	}
	
	@Override
	public Class<T> getMappedClass() {
		return mappedClass;
	}
	
}
