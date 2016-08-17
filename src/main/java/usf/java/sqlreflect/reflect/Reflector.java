package usf.java.sqlreflect.reflect;

import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<T, A extends Adapter<T>> {
	
	void run(A adapter) throws Exception;
	
	List<T> run() throws Exception;
	
}