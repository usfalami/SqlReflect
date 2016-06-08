package usf.java.sql.reflect.core.scanner;

import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Database;
import usf.java.sql.db.field.Function;
import usf.java.sql.reflect.core.Reflector;

public interface Scanner<T extends Scanner.Adapter> extends Reflector<T> {
	
	public static interface Adapter extends Reflector.Adapter {

		void start();
		void finish();
		
	}
	
	public static interface DatabaseAdapter extends Scanner.Adapter {
		
		void performDatabase(Database db);
	
	}
	
	public static interface FunctionAdapter extends Scanner.Adapter {
		
		void performFunction(Function procedure, Column ...columns);
	
	}
	
		
}
