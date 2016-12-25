package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.sql.type.DatabaseType;

public interface Mapper<T> {
	
	 Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws Exception;
	
	T map(ResultSet rs, int row) throws Exception;
	
	Class<T> getMappedClass();
	
}