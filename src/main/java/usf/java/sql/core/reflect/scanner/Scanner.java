package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.field.Field;
import usf.java.sql.core.reflect.Reflector;

public interface Scanner extends Reflector {
	
	public static interface HasScanner<T extends Field> extends HasReflector {

		void start();
		void adapte(T field);
		void finish();
		
	}
		
}
