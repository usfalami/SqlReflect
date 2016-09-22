package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector<C extends ConnectionManager> {
	
	private C cm;
	
	public AbstractReflector(C cm) {
		this.cm = cm;
	}
	
	public C getConnectionManager() {
		return cm;
	}

}