package usf.java.sqlreflect.adapter;

import java.util.Collection;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.ActionTimer;

public interface Adapter<T> {
	
	void start() throws Exception;

	void prepare(Class<T> clazz, Collection<Property> headers) throws Exception; //add mappedClass parameter
	
	void adapte(T field, int index) throws Exception;
	
	void end(ActionTimer at) throws Exception;

}
