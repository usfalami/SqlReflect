package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.List;

import usf.java.sql.adapter.reflect.scanner.ListAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.Reflector;

public abstract class AbstractFieldScanner<T> extends Reflector implements Scanner<T> {
	
	public AbstractFieldScanner(ConnectionManager cm) {
		super(cm);
	}

	@Override
	public List<T> run() throws SQLException, AdapterException {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter);
		return adapter.getList();
	}
	
	@Override
	public void run(ScannerAdapter<T> adapter) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.getConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}

	protected abstract void run(DatabaseMetaData dm, ScannerAdapter<T> adapter) throws SQLException, AdapterException;

}
