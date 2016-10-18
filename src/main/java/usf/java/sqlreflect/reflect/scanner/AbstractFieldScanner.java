package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;

public abstract class AbstractFieldScanner<R> extends AbstractReflector<ConnectionManager, R> implements Scanner {
	
	public AbstractFieldScanner(ConnectionManager cm) {
		super(cm);
	}
	public AbstractFieldScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		try {
			
			ActionTimer action = at.startAction(Constants.ACTION_CONNECTION);
			getConnectionManager().openConnection();
			action.end();
			
			DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
			runScan(dm, adapter, at);
			
		}finally {
			getConnectionManager().close();
		}
	}

	protected abstract void runScan(DatabaseMetaData dm, Adapter<R> adapter, ActionTimer at) throws Exception;
}
