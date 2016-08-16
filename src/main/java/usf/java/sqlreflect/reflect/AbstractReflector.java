package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector {
	
	private ConnectionManager cm;
	
	public AbstractReflector(ConnectionManager cm) {
		this.cm = cm;
	}
	
	public ConnectionManager getConnectionManager() {
		return cm;
	}

}