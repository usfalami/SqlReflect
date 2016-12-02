package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Mapper<T> {
	
	void prepare(ResultSet rs, DatabaseType type) throws SQLException;
	
	T map(ResultSet rs, int row) throws Exception;

	void write(StreamWriter writer, T field) throws Exception;
	
	String[] getColumnNames();
	
}