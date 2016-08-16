package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.exception.AdapterException;

public interface Adapter {
	
	void start() throws AdapterException;
	void end()  throws AdapterException;

}
