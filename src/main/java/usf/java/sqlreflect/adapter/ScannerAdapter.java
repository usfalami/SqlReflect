package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Mapper;

public interface ScannerAdapter<T> extends Adapter {
	
	void prepare(Mapper<T> mapper) throws Exception;
	void adapte(T field, int index) throws Exception;
	
}