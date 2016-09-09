package usf.java.sqlreflect.reflect.scanner;

import java.io.Serializable;
import java.sql.Statement;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Arguments;
import usf.java.sqlreflect.field.Query;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

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
	public final void run(Adapter<T> adapter) throws Exception {
		TimePerform tp = new TimePerform();
		ActionPerform total = tp.startAction(Constants.ACTION_TOTAL);
		try {
			adapter.start();

			ActionPerform action = tp.startAction(Constants.ACTION_CONNECTION);
			getConnectionManager().openConnection();
			action.end();
			
			Statement stmt = null;
			try {

				action = tp.startAction(Constants.ACTION_STATEMENT);
				stmt = getConnectionManager().buildStatement(query, args);
				action.end();
				
				run(stmt, adapter, tp);
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
			getConnectionManager().close();
			total.end();
			adapter.end(tp);
		}
	}
	
	public Query getCallable() {
		return query;
	}
	
	public Arguments getParameters() {
		return args;
	}

	protected abstract void run(Statement stmt, Adapter<T> adapter, TimePerform tp) throws Exception;

}
