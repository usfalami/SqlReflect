package usf.java.sql.core.reflect;

import usf.java.sql.core.connection.ConnectionManager;


public interface Reflector {
	
	public static interface HasReflector {

		ConnectionManager getConnectionManager();
		
	}

}
