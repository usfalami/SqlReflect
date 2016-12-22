package usf.java.sqlreflect.mapper;

import java.util.Collection;

public interface PropertyMapper<T> {

	void prepare(Collection<Filter> headers) throws Exception;
	
	void setProperty(T obj, String propertyName, Object value) throws Exception; 
}
