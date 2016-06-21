package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.mapper.Mapper;

public interface Scanner {
	
	public static interface HasScanner<T> {
		
		void start();
		void adapte(T field, int index);
		void end();
		Mapper<T> getMapper();
		
	}
	
}
