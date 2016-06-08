package usf.java.sql.reflect;

import usf.java.sql.connection.ConnectionManager;


public interface Reflector<T extends Reflector.Adapter> {
	
	public static interface Adapter {

		ConnectionManager getConnectionManager();
	
	}

}
