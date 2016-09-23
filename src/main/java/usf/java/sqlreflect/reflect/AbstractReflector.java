package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector<T extends ConnectionManager> {
	
	private T cm;
	private TimePerform tp;
	
	public AbstractReflector(T cm) {
		this(cm, new TimePerform());
	}
	public AbstractReflector(T cm, TimePerform tp) {
		this.cm = cm;
		this.tp = tp;
	}
	
	public T getConnectionManager() {
		return cm;
	}
	
	public TimePerform getTimePerform() {
		return tp;
	}
}