package usf.java.sql.core.reflect;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;

public abstract class Reflector {
	
	private ConnectionManager cm;
	
	public Reflector(ConnectionManager cm) {
		this.cm = cm;
	}
	
	public ConnectionManager getConnectionManager() {
		return cm;
	}
	
	public static interface Adapter {
		
		void start() throws AdapterException;
		void end()  throws AdapterException;
	
	}

}
