package usf.java.sqlreflect.adapter;

import java.util.List;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Header;

public interface Adapter<T> {
	
	void start() throws Exception;

	void prepare(List<Header> headers, Class<T> clazz) throws Exception; //add mappedClass parameter
	
	void adapte(T field, int index) throws Exception;
	
	void end(ActionTimer at) throws Exception;

}
