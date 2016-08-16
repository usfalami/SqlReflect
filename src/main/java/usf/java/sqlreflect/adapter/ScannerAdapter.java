package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.mapper.Mapper;

public interface ScannerAdapter<T> extends Adapter {
	
	void prepare(Mapper<T> mapper) throws AdapterException;
	void adapte(T field, int index) throws AdapterException;
	
}