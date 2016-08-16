package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.mapper.Mapper;

public interface ScannerAdapter<T> extends Adapter {
	
	void headers(Mapper<T> mapper) throws AdapterException;
	void adapte(T field, int index) throws AdapterException;
	
}