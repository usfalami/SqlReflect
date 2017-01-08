package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

public interface Template<T> {

	void prepare(ResultSetMetaData metaData) throws Exception;

	T get(ResultSet rs) throws Exception;
	
	List<Field<?>> getFields();
	
}
