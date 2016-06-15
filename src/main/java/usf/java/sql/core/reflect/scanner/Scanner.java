package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.reflect.Reflector;

public interface Scanner extends Reflector {
	
	public static interface HasScanner extends Reflector.HasReflector {

		void start();
		void finish();
		
	}
	
	public static interface HasDatabaseScanner<T extends Database> extends HasScanner {
		
		void adapte(T db);
	
	}
	
	public static interface HasCallableScanner<T extends Function> extends HasScanner {
		
		void adapte(T procedure, Column ...columns);

	}
	
}
