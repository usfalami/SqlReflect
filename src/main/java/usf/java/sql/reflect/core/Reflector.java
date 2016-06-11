package usf.java.sql.reflect.core;

import usf.java.sql.connection.ConnectionManager;


public interface Reflector<T extends Reflector.HasReflector> {
	
	public static interface HasReflector {

		ConnectionManager getConnectionManager();
	
	}

}
