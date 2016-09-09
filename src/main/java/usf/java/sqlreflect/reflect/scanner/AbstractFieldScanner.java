package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractFieldScanner<T> extends AbstractReflector implements Scanner<T> {
	
	public AbstractFieldScanner(ConnectionManager cm) {
		super(cm);
	}

	@Override
	public final List<T> run() throws Exception {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter);
		return adapter.getList();
	}
	
	@Override
	public final void run(Adapter<T> adapter) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(TOTAL);
		try {
			adapter.start();
			
			ActionPerform action = tp.startAction(CONNECTION);
			getConnectionManager().openConnexion();
			action.end();
			
			DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
			run(dm, adapter, tp);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close();
			total.end();
			adapter.end(tp);
		}
	}

	protected abstract void run(DatabaseMetaData dm, Adapter<T> adapter, TimePerform tp) throws Exception;

}
