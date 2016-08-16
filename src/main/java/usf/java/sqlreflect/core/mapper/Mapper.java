package usf.java.sqlreflect.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.core.stream.StreamWriter;

public interface Mapper<T> {
	
	T map(ResultSet rs, int row) throws SQLException;

	void write(StreamWriter writer, T field) throws Exception;
	
	String[] getColumnNames();

	void setColumnNames(String... columnNames);
	
}