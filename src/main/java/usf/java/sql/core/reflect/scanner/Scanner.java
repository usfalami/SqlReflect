package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.reflect.Reflector.Adapter;
import usf.java.sql.core.reflect.exception.AdapterException;

public interface Scanner {
	
	public static interface ScannerAdapter<T> extends Adapter {
		
		void headers(String... headers);
		void adapte(T field, int index) throws AdapterException;
		
	}
	
}
