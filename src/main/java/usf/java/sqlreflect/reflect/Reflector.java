package usf.java.sqlreflect.reflect;

import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<T> {
	
	void run(Adapter<T> adapter) throws Exception;
	
	List<T> run() throws Exception;
	
}