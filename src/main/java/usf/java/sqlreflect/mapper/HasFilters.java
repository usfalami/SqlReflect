package usf.java.sqlreflect.mapper;

import usf.java.sqlreflect.mapper.filter.ValueConverter;

public interface HasFilters {
	
	void addMapperFilter(String columnName, String mappedName, ValueConverter<?> converter) ;
	
	void addMapperFilter(String columnName, String mappedName);
	
	void addMapperFilter(String columnName, ValueConverter<?> converter);
	
}
