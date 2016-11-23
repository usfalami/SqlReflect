package usf.java.sqlreflect.reflect;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
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

	@Override
	public final void run(Adapter<R> adapter) throws Exception {
		timer.setName(getClass().getSimpleName()).start();
		try {
			ActionTimer action = null;
			adapter.start();

			action = timer.startAction(Constants.ACTION_VALIDATION);
			validateArgs();
			action.end();
			
			action = timer.startAction(Constants.ACTION_CONNECTION);
			getConnectionManager().openConnection();
			action.end(); //ACTION_CONNECTION end

			run(adapter, timer);
		}catch (Exception e) {
			timer.setMessage(e.getMessage());
			throw e;
		} finally {
			getConnectionManager().close();
			timer.end();
			adapter.end(timer);
		}
	}
	
	protected void validateArgs() {
		if(cm == null) throw new IllegalArgumentException("ConnectionManager can't be null");
	}
	
	public abstract void run(Adapter<R> adapter, ActionTimer at) throws Exception;
		
	public C getConnectionManager() {
		return cm;
	}
}