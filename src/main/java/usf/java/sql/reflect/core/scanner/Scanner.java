package usf.java.sql.reflect.core.scanner;

import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Database;
import usf.java.sql.db.field.Callable;
import usf.java.sql.reflect.core.Reflector;

public interface Scanner<T extends Scanner.HasAdapter> extends Reflector<T> {
	
	public static interface HasAdapter extends Reflector.Adapter {

		void start();
		void finish();
		
	}
	
	public static interface HasDatabaseAdapter extends Scanner.HasAdapter {
		
		void adapte(Database db);
	
	}
	
	public static interface HasFunctionAdapter extends Scanner.HasAdapter {
		
		void adapte(Callable procedure, Column ...columns);

	}
	
		
}
