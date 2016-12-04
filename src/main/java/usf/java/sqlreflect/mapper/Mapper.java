package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.writer.Writer;

public interface Mapper<T> extends Writer<T> {
	
	void prepare(ResultSet rs, DatabaseType type) throws SQLException;
	
	T map(ResultSet rs, int row) throws Exception;

	String[] getColumnNames();
	
}