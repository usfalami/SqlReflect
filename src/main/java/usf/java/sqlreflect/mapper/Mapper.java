package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.type.DatabaseType;

public interface Mapper<T> {
	
	 Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException, Exception;
	
	T map(ResultSet rs, int row) throws Exception;
	
}