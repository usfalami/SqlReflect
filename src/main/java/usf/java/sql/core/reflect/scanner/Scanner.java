package usf.java.sql.core.reflect.scanner;

import usf.java.sql.core.db.field.Callable;
import usf.java.sql.core.db.field.Column;
import usf.java.sql.core.db.field.Database;
import usf.java.sql.core.reflect.Reflector;

public interface Scanner extends Reflector {
	
	public static interface HasScanner extends Reflector.HasReflector {

		void start();
		void finish();
		
	}
	
	public static interface HasDatabaseScanner<T extends Database> extends HasScanner {
		
		void adapte(T db);
	
	}
	
	public static interface HasCallableScanner<T extends Callable> extends HasScanner {//T can be a proceduure, macro or function
		
		void adapte(T procedure, Column ...columns);

	}
	
}
