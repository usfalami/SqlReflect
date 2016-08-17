package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<A extends Adapter<?>> {
	
	void run(A adapter) throws Exception;
	
}