package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.reflect.ActionTimer;

public interface Adapter<T> {
	
	void start() throws Exception;

	void prepare(ComplexObject<T> complexObject) throws Exception; //add mappedClass parameter
	
	void adapte(T field, int index) throws Exception;
	
	void end(ActionTimer at) throws Exception;

}
