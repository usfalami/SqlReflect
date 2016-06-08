package usf.java.sql.reflect.parser;

import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Database;
import usf.java.sql.db.field.Function;
import usf.java.sql.reflect.Reflector;

public interface Parser<T extends Parser.Adapter> extends Reflector<T> {
	
	public static interface Adapter extends Reflector.Adapter {

		public abstract void start();
		public abstract void finish();
		
	}
	
	public static interface DatabaseAdapter extends Parser.Adapter {
		
		void performDatabase(Database db);
	
	}
	
	public static interface FunctionAdapter extends Parser.Adapter {
		
		void performFunction(Function procedure, Column ...columns);
	
	}
	
		
}
