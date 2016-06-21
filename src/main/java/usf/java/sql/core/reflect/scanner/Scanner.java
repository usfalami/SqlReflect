package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.exception.AdapterException;

public interface Scanner {
	
	public static interface HasScanner<T> {
		
		void start() throws AdapterException;
		void adapte(T field, int index) throws AdapterException;
		void end() throws AdapterException;
		Mapper<T> getMapper();
		
	}
	
}
