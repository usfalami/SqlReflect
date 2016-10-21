package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;

public class BatchExecutor<A> extends AbstractStatementExecutor<Integer[]> {

	private String[] queries;
	private Collection<A> argsList;
	private Binder<A> binder;

	public BatchExecutor(TransactionManager cm) {
		super(cm);
	}
	public BatchExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected Statement runStatement() throws SQLException {
		TransactionManager tm = getConnectionManager();
		return queries.length > 1 || Utils.isEmpty(argsList) ? tm.buildBatch(queries) : tm.buildBatch(queries[0], argsList, binder);
	}
	@Override
	protected Integer[] runExecution(Statement stmt) throws SQLException {
		return Utils.convert(stmt.executeBatch());
	}

	public BatchExecutor<A> set(String sql, Collection<A> argsList, Binder<A> binder) throws Exception {
		this.queries = new String[]{sql};
		this.argsList = argsList;
		this.binder = binder;
		return this;
	}
	public BatchExecutor<A> set(String... sql) throws Exception {
		this.queries = sql;
		this.argsList = null;
		this.binder = null;
		return this;
	}
	
}
