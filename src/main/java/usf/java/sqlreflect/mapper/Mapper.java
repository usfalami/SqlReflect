package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.writer.TypeWriter;

public interface Mapper<T> {
	
	void prepare(ResultSet rs, DatabaseType type) throws SQLException;
	
	T map(ResultSet rs, int row) throws Exception;
	
	Map<String, TypeWriter> getTypes() throws SQLException;
	
}