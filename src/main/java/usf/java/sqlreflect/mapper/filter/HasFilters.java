package usf.java.sqlreflect.mapper.filter;

import usf.java.sqlreflect.mapper.filter.converter.Converter;

public interface HasFilters {
	
	void addFilter(String columnName, String propertyName, Converter<?> converter);
	
	void addFilter(String columnName, Converter<?> converter);
	
	void addFilter(String columnName, String propertyName);
	
}
