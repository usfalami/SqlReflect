package usf.java.sql.core.reflect;

import usf.java.sql.core.connection.manager.ConnectionManager;

public abstract class Reflector {
	
	private ConnectionManager cm;
	
	public Reflector(ConnectionManager cm) {
		this.cm = cm;
	}
	
	public ConnectionManager getConnectionManager() {
		return cm;
	}

}
