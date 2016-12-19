package usf.java.sqlreflect.mapper.filter;

public interface HasFilters {
	
	void addFilter(String columnName, String propertyName, ResultConverter<?> converter);
	
	void addFilter(String columnName, ResultConverter<?> converter);
	
	void addFilter(String columnName, String propertyName);
	
}
