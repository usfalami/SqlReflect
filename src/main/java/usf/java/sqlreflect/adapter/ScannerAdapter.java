package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Mapper;

public interface ScannerAdapter<T> extends Adapter<T> {
	
	void prepare(Mapper<T> mapper) throws Exception;
	
}