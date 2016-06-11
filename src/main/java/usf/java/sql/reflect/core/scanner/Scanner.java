package usf.java.sql.reflect.core.scanner;

import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Database;
import usf.java.sql.db.field.Callable;
import usf.java.sql.reflect.core.Reflector;

public interface Scanner<T extends Scanner.HasScanner> extends Reflector<T> {
	
	public static interface HasScanner extends Reflector.Adapter {

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
