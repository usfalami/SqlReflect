package usf.java.sqlreflect.mapper;

import usf.java.sqlreflect.mapper.filter.ResultConverter;

public interface HasFilters {
	
	void addFilter(String columnName, String propertyName, ResultConverter<?> converter);
	
	void addFilter(String columnName, ResultConverter<?> converter);
	
	void addFilter(String columnName, String propertyName);
	
}
