package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;

public class UpdateExecutor<A> extends AbstractStatementExecutor<Integer> {

	private String query;
	private Binder<A> binder;
	private A args;

	public UpdateExecutor(TransactionManager cm) {
		super(cm);
	}
	public UpdateExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected Statement runPreparation() throws SQLException {
		return getConnectionManager().prepare(query, args, binder);
	}
	@Override
	protected Integer runExecution(Statement stmt) throws SQLException {
		return getConnectionManager().executeUpdate(stmt, query, args, binder);
	}
	
	@Override
	protected void validateArgs() {
		super.validateArgs();
		if(Utils.isEmptyString(query)) throw new IllegalArgumentException("Query can't be null");
		if(Utils.isIllegalArg(args, binder)) throw new IllegalArgumentException("Query parameters are not valid");
	}
	
	public UpdateExecutor<A> set(String sql, A args, Binder<A> binder) {
		this.query = sql;
		this.args = args;
		this.binder = binder;
		return this;
	}
	public UpdateExecutor<A> set(String sql) {
		return set(sql, null, null);
	}

}
