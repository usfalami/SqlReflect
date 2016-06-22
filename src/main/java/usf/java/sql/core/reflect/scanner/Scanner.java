package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.Reflector.Adapter;
import usf.java.sql.core.reflect.exception.AdapterException;

public interface Scanner {
	
	public static interface ScannerAdapter<T> extends Adapter {
		
		void adapte(T field, int index) throws AdapterException;
		
	}
	
	public static interface ScannerDynamicAdapter<T> extends ScannerAdapter<T> {

		Mapper<T> getMapper();
		
	}
	
}
