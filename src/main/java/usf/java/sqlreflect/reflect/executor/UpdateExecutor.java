package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.Runnable;

public class UpdateExecutor<A> extends AbstractStatementExecutor<Integer> {

	private Runnable query;
	private Binder<A> binder;
	private A args;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	public UpdateExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected Statement runStatement() throws SQLException {
		return getConnectionManager().buildStatement(query, args, binder);
	}
	
	@Override
	protected Integer runExecution(Statement stmt) throws SQLException {
		return getConnectionManager().executeUpdate(stmt, query, args, binder);
	}

	
	public UpdateExecutor<A> set(String sql, A args, Binder<A> binder) {
		this.query = getConnectionManager().getSqlParser().parseSQL(sql);
		this.args = args;
		this.binder = binder;
		return this;
	}
	public UpdateExecutor<A> set(String sql) {
		return this.set(sql, null, null);
	}

}
