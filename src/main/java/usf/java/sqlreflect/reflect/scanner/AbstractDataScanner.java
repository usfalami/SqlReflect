package usf.java.sqlreflect.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.Arguments;

public abstract class AbstractDataScanner<T> extends AbstractReflector implements Scanner<T> {
	
	private Query query;
	private Arguments args;

	public AbstractDataScanner(ConnectionManager cm) {
		super(cm);
	}

	public AbstractDataScanner<T> set(String sql, Serializable... parameters) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = new Arguments(parameters);
		return this;
	}
	
	@Override
	public final List<T> run() throws SQLException, AdapterException {
		ListAdapter<T> adapter = new ListAdapter<T>();
		this.run(adapter);
		return adapter.getList();
	}

	@Override
	public final void run(ScannerAdapter<T> adapter) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = getConnectionManager().getConnection();
			Statement stmt = null;
			try {
				stmt = getConnectionManager().buildStatement(cnx, query, args);
				run(stmt, adapter);
			} catch (SQLException e) {
				throw e;
			}
			finally {
				getConnectionManager().close(stmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close(cnx);
		}
	}
	
	public Query getCallable() {
		return query;
	}
	
	public Arguments getParameters() {
		return args;
	}

	protected abstract void run(Statement stmt, ScannerAdapter<T> adapter) throws SQLException, AdapterException;

}
