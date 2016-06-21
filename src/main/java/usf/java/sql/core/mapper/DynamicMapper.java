package usf.java.sql.core.mapper;

public interface DynamicMapper<T> extends Mapper<T> {
	
	void setColumnNames(String... columnNames);
	
	String[] getColumnNames();

}
