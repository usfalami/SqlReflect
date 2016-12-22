package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.sql.entry.Header;

public interface PropertyMapper<T> {

	void prepare(List<Header> headers) throws SQLException;
	
	void setProperty(T obj, String propertyName, Object value) throws Exception; 
}
