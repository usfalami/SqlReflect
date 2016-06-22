package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Reflector.Adapter;

public interface Scanner {
	
	public static interface ScannerAdapter<T> extends Adapter {
		
		void headers(String... headers);
		void adapte(T field, int index) throws AdapterException;
		
	}
	
}
