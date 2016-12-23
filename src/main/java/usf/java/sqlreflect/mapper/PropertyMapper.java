package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;

public interface PropertyMapper<T> {

	void prepare(Collection<Metadata> headers) throws SQLException;
	
	void setProperty(T obj, String propertyName, Object value) throws Exception; 
}
