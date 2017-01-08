package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.List;

import usf.java.sqlreflect.stream.StreamWriter;

public interface Template<T> {

	List<Field<?>> getFields();
	
	void prepare(ResultSetMetaData metaData) throws Exception;

	T map(ResultSet rs) throws Exception;

	void write(StreamWriter sw, T obj) throws Exception;
	
}
