package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

public interface Generic<T> {
	
	T map(ResultSet rs) throws Exception;

}
