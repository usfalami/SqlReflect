package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;

public interface Adapter {
	
	void start() throws AdapterException;
	void end()  throws AdapterException;

}
