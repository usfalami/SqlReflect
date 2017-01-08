package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

public interface Generic<T> {
	
	T get(ResultSet rs) throws Exception;

}
