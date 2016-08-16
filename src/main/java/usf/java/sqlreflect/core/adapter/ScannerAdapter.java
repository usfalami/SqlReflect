package usf.java.sqlreflect.core.adapter;

import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.mapper.Mapper;

public interface ScannerAdapter<T> extends Adapter {
	
	void prepare(Mapper<T> mapper) throws AdapterException;
	void adapte(T field, int index) throws AdapterException;
	
}