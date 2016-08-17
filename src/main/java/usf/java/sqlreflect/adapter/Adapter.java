package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.TimePerform;

public interface Adapter<T> {
	
	void start() throws Exception;
	
	void adapte(T field, int index) throws Exception;
	
	void end(TimePerform time) throws Exception;

}
