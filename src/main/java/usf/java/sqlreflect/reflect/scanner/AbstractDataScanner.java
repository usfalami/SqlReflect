package usf.java.sqlreflect.reflect.scanner;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.reflect.AbstractReflector;

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
			Statement stmt = null;
			try {
				stmt = getConnectionManager().buildStatement(cnx, query, args);
				run(stmt, adapter);
			} catch (Exception e) {
				throw e;
			}
			finally {
				getConnectionManager().close(stmt);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			getConnectionManager().close(cnx);
			adapter.end();
		}
	}
	
	public Query getCallable() {
		return query;
	}
	
	public Arguments getParameters() {
		return args;
	}

	protected abstract void run(Statement stmt, ScannerAdapter<T> adapter) throws Exception;

}
