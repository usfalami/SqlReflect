package usf.java.sqlreflect.reflect;

import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector<C extends ConnectionManager, R> implements Reflector<R> {
	
	private C cm;
	private ActionTimer at;
	
	public AbstractReflector(C cm) {
		this(cm, new ActionTimer());
	}
	public AbstractReflector(C cm, ActionTimer at) {
		this.cm = cm;
		this.at = at;
	}
	
	public C getConnectionManager() {
		return cm;
	}
	
	@Override
	public final List<R> run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		return adapter.getList();
	}

	@Override
	public final void run(Adapter<R> adapter) throws Exception {
		at.setName(getClass().getSimpleName());
		try {
			adapter.start();
			
			at.start();
			run(adapter, at);
			at.end();
		
		} finally {
			adapter.end(at);
		}
	}
	
	public abstract void run(Adapter<R> adapter, ActionTimer at) throws Exception;
	
}