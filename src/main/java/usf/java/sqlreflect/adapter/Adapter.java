package usf.java.sqlreflect.adapter;

import java.util.Collection;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.ActionTimer;

public interface Adapter<T> {
	
	void start() throws Exception;

	void prepare(Collection<Metadata> headers, Class<T> clazz) throws Exception; //add mappedClass parameter
	
	void adapte(T field, int index) throws Exception;
	
	void end(ActionTimer at) throws Exception;

}
