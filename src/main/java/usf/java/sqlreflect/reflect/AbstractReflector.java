package usf.java.sqlreflect.reflect;

import java.util.List;

import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector<C extends ConnectionManager, R> implements Reflector<R> {
	
	private C cm;
	private TimePerform tp;
	
	public AbstractReflector(C cm) {
		this(cm, new TimePerform());
	}
	public AbstractReflector(C cm, TimePerform tp) {
		this.cm = cm;
		this.tp = tp;
	}
	
	public C getConnectionManager() {
		return cm;
	}
	
	public TimePerform getTimePerform() {
		return tp;
	}
	
	@Override
	public List<R> run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		return adapter.getList();
	}
	
}