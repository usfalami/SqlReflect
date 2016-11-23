package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;

public class MultiQueyExecutor<A> extends AbstractStatementExecutor<Boolean> {

	private String[] queries;

	public MultiQueyExecutor(TransactionManager cm) {
		super(cm);
	}
	public MultiQueyExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected Statement runPreparation() throws SQLException {
		return getConnectionManager().getConnection().createStatement();
	}
	@Override
	protected Boolean runExecution(Statement stmt) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for(String query : queries) 
			sb.append(query).append(";");
		return getConnectionManager().execute(stmt, sb.toString());
	}
	
	@Override
	protected void validateArgs() {
		super.validateArgs();
		if(Utils.isEmptyArray(queries)) throw new IllegalArgumentException("Queries can't be null or empty");
	}
	
	public MultiQueyExecutor<A> set(String... queries) {
		this.queries = queries;
		return this;
	}
}
