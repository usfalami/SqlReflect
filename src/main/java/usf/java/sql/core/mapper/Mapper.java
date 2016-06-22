package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.stream.StreamWriter;

public interface Mapper<T> {
	
	T map(ResultSet rs, int row) throws SQLException;

	void write(StreamWriter writer, T field) throws Exception;
	
	String[] getColumnNames();
	
}