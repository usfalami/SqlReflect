package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractFieldScanner<T> extends AbstractReflector<ConnectionManager> implements Scanner<T> {
	
	public AbstractFieldScanner(ConnectionManager cm) {
		super(cm);
	}
	public AbstractFieldScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	protected void run(Adapter<T> adapter, String arg1, String arg2, String arg3) throws Exception {
		ActionPerform total = getTimePerform().startAction(getClass().getSimpleName());
		try {
			adapter.start();
			
			ActionPerform action = getTimePerform().startAction(Constants.ACTION_CONNECTION);
			getConnectionManager().openConnection();
			action.end();
			
			DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
			runScan(dm, adapter, arg1, arg2, arg3);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close();
			total.end();
			adapter.end(getTimePerform());
		}
	}

	protected abstract void runScan(DatabaseMetaData dm, Adapter<T> adapter, String arg1, String arg2, String arg3) throws Exception;
	
	@Override
	public final List<T> run() throws Exception {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter, null, null, null);
		return adapter.getList();
	}
	@Override
	public void run(Adapter<T> adapter) throws Exception {
		this.run(adapter, null, null, null);
	}
	
}
