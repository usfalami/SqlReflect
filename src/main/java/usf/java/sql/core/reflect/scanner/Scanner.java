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
	
	public static interface HasDatabaseScanner extends Scanner.HasScanner {
		
		void adapte(Database db);
	
	}
	
	public static interface HasCallableScanner extends Scanner.HasScanner {
		
		void adapte(Callable procedure, Column ...columns);

	}
	
}
