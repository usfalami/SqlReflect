package usf.java.sql.core.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Worker;
import usf.java.sql.core.reflect.Reflector.Adapter;

public interface Scanner<T> extends Worker<Scanner.ScannerAdapter<T>> {
	
	List<T> run() throws SQLException, AdapterException;
	
	public static interface ScannerAdapter<T> extends Adapter {
		
		void headers(String... headers) throws AdapterException;
		void adapte(T field, int index) throws AdapterException;
		
	}
	
}
