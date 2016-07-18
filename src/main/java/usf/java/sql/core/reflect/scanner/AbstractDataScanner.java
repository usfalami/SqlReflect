package usf.java.sql.core.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import usf.java.sql.core.adapter.ListAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.reflect.Reflector;

public abstract class AbstractDataScanner<T> extends Reflector implements Scanner<T> {
	
	protected Query callable; 
	protected Serializable[] parameters;

	public AbstractDataScanner(ConnectionManager cm) {
		super(cm);
	}

	public AbstractDataScanner<T> set(Query callable, Serializable[] parameters) {
		this.callable = callable;
		this.parameters = parameters;
		return this;
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
			Statement stmt = null;
			try {
				stmt = cm.buildStatement(cnx, callable.getSQL(), parameters);
				run(stmt, adapter);
			} catch (SQLException e) {
				e.printStackTrace();
				throw e;
			}
			finally {
				cm.close(stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}

	protected abstract void run(Statement stmt, ScannerAdapter<T> adapter) throws SQLException, AdapterException;

}
