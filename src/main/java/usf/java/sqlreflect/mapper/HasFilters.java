package usf.java.sqlreflect.mapper;

import usf.java.sqlreflect.mapper.filter.ValueConverter;

public interface HasFilters {
	
	void addFilter(String columnName, String mappedName, ValueConverter<?> converter) ;
	
	void addFilter(String columnName, String mappedName);
	
	void addFilter(String columnName, ValueConverter<?> converter);
	
}
