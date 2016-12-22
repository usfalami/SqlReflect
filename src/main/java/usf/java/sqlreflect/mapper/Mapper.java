package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public interface Mapper<T> {
	
	List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException, Exception;
	
	T map(ResultSet rs, int row) throws Exception;
	
	Class<T> getMappedClass();
}