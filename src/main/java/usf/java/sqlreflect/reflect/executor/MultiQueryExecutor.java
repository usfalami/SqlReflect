package usf.java.sqlreflect.reflect.executor;

import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.reflect.ActionTimer;

public class MultiQueryExecutor<A> extends AbstractStatementExecutor<Boolean> {

	private String[] queries;

	public MultiQueryExecutor(TransactionManager cm) {
		super(cm);
	}
	public MultiQueryExecutor(TransactionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected Statement runStatement() throws SQLException {
		return getConnectionManager().prepare(null, null, null);
	}
	@Override
	protected Boolean runExecution(Statement stmt) throws SQLException {
		StringBuilder sb = new StringBuilder();
		for(String query : queries) 
			sb.append(query).append(";");
		return getConnectionManager().execute(stmt, sb.toString());
	}
	
	public MultiQueryExecutor<A> set(String... queries) {
		this.queries = queries;
		return this;
	}
}
