package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public abstract class AbstractFieldScanner<R> extends AbstractReflector<ConnectionManager, R> implements Scanner {
	
	public AbstractFieldScanner(ConnectionManager cm) {
		super(cm);
	}
	public AbstractFieldScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	@Override
	public void run(Adapter<R> adapter) throws Exception {
		ActionPerform total = getTimePerform().startAction(getClass().getSimpleName());
		try {
			adapter.start();
			
			ActionPerform action = getTimePerform().startAction(Constants.ACTION_CONNECTION);
			getConnectionManager().openConnection();
			action.end();
			
			DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
			runScan(dm, adapter);
			
		}finally {
			getConnectionManager().close();
			total.end();
			adapter.end(getTimePerform());
		}
	}

	protected abstract void runScan(DatabaseMetaData dm, Adapter<R> adapter) throws Exception;
}
