package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector<T extends ConnectionManager> {
	
	private T cm;
	
	public AbstractReflector(T cm) {
		this.cm = cm;
	}
	
	public T getConnectionManager() {
		return cm;
	}

}