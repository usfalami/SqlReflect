package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.adapter.Adapter;

public interface Reflector<T extends Adapter> {
	
	void run(T adapter) throws Exception;
	
}
