package usf.java.sqlreflect.reflect;

import java.util.Collection;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;

public abstract class AbstractReflector<C extends ConnectionManager, R> implements Reflector<R> {
	
	private C cm;
	private ActionTimer timer;
	
	public AbstractReflector(C cm) {
		this(cm, new ActionTimer());
	}
	public AbstractReflector(C cm, ActionTimer timer) {
		this.cm = cm;
		this.timer = timer;
	}
	
	public C getConnectionManager() {
		return cm;
	}

	@Override
	public final void run(Adapter<R> adapter) throws Exception {
		timer.setName(getClass().getSimpleName());
		try {
			timer.start();
			adapter.start();
			try {
				
				ActionTimer action = timer.startAction(Constants.ACTION_CONNECTION);
				getConnectionManager().openConnection();
				action.end();

				run(adapter, timer);
				
			} finally {
				getConnectionManager().close();
			}
		} finally {
			timer.end();
			adapter.end(timer);
		}
	}
	
	@Override
	public final Collection<R> run() throws Exception {
		ListAdapter<R> adapter = new ListAdapter<R>();
		run(adapter);
		return adapter.getList();
	}
	
	public abstract void run(Adapter<R> adapter, ActionTimer at) throws Exception;
	
}