package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

public interface Generic<T> {
	
	public abstract T get(ResultSet rs) throws Exception;

}
