package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<R> {

	void run(Adapter<R> adapter) throws Exception;
	
}