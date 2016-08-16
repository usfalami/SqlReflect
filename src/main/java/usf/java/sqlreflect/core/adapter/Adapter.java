package usf.java.sqlreflect.core.adapter;

import usf.java.sqlreflect.core.exception.AdapterException;

public interface Adapter {
	
	void start() throws AdapterException;
	void end()  throws AdapterException;

}
