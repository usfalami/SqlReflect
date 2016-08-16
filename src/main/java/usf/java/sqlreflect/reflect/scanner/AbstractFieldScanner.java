package usf.java.sqlreflect.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.util.List;

import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.reflect.AbstractReflector;

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
	public final void run(ScannerAdapter<T> adapter) throws Exception {
		Connection cnx = null;
		try {
			adapter.start();
			cnx = getConnectionManager().getConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close(cnx);
			adapter.end();
		}
	}

	protected abstract void run(DatabaseMetaData dm, ScannerAdapter<T> adapter) throws Exception;

}
