package usf.java.sql.reflect.core;

import usf.java.sql.connection.ConnectionManager;


public interface Reflector<T extends Reflector.Adapter> {
	
	public static interface Adapter {

		ConnectionManager getConnectionManager();
	
	}

}
