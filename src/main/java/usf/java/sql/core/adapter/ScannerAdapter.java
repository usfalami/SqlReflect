package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;

public interface ScannerAdapter<T> extends Adapter {
	
	void headers(String... headers) throws AdapterException;
	void adapte(T field, int index) throws AdapterException;
	
}