package usf.java.sqlreflect.mapper.tmp;

import usf.java.sqlreflect.mapper.Mapper;

public interface AdvancedMapper<T> extends Mapper<T> {
	
	void addMapperFilter(String columnName, String mappedName, ValueConverter<?> converter) ;
	
	void addMapperFilter(String columnName, String mappedName);
	
	void addMapperFilter(String columnName, ValueConverter<?> converter);
	
}
