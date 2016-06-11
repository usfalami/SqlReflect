package usf.java.sql.reflect.adapter.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.SQL;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.AbstractReflectorAdapter;
import usf.java.sql.reflect.core.executor.Executor;
import usf.java.sql.reflect.core.executor.PreparedStatementExecutor;
import usf.java.sql.reflect.core.executor.StatementExecutor;

public abstract class AbstractExecutorAdapter extends AbstractReflectorAdapter implements Executor.HasExecutor {
	
	public AbstractExecutorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void execute(String query, Serializable... parameters) throws SQLException { //one preparedStatement
		Executor e = executorFor(parameters);
		SQL sql = cm.parseSQL(query);
		if(sql != null)
			e.run(this, sql, parameters);
	}
	public void execute(String... queries) throws SQLException { // only statments
		if(queries == null) return;
		Executor e = new StatementExecutor();
		for(String query : queries)
			e.run(this, cm.parseSQL(query));
	}
	public void execute(String[] queries, Serializable[][] parameters) throws SQLException {
		if(queries == null || parameters==null || queries.length != parameters.length) return; //throw exception
		for(int i=0; i<queries.length; i++)
			this.execute(queries[i], parameters[i]);
	}
	
	protected Executor executorFor(Serializable... parameters) {
		return parameters==null || parameters.length==0 ? new StatementExecutor() : new PreparedStatementExecutor();
	}
	
	public static int rowsCount(ResultSet rs) throws SQLException{
		int count = 0;
		if(rs.next()){
			rs.last();
			count=rs.getRow();
			rs.beforeFirst();
		}
		return count;
	}

}
