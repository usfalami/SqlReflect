package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.stream.StreamWriter;

public interface Mapper<T> {
	
	T map(ResultSet rs, int row) throws Exception;

	void write(StreamWriter writer, T field) throws Exception;
	
	String[] getColumnNames();

	void setColumnNames(String... columnNames);
	
}