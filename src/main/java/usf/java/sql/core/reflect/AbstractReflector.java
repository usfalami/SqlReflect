package usf.java.sql.core.reflect;

import usf.java.sql.core.connection.manager.ConnectionManager;

public abstract class AbstractReflector {
	
	private ConnectionManager cm;
	
	public AbstractReflector(ConnectionManager cm) {
		this.cm = cm;
	}
	
	public ConnectionManager getConnectionManager() {
		return cm;
	}

}