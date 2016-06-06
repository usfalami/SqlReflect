package usf.java.reflect.executor.adapter;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.field.SQL;
import usf.java.formatter.Formatter;
import usf.java.reflect.AbstractAdapter;
import usf.java.reflect.executor.Executor;
import usf.java.reflect.executor.PreparedStatmentExecutor;
import usf.java.reflect.executor.StatmentExecutor;

public abstract class ExecutorAdapter extends AbstractAdapter {
	
	public ExecutorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void execute(String query, Serializable... parameters) throws SQLException {
		Executor e = executorFor(parameters);
		SQL sql = cm.parseSQL(query);
		if(sql != null)
			e.run(this, sql, parameters);
	}
	public void execute(String... queries) throws SQLException { // only statment
		if(queries == null) return;
		Executor e = new StatmentExecutor();
		for(String query : queries)
			e.run(this, cm.parseSQL(query));
	}
	public void execute(String[] queries, Serializable[][] parameters) throws SQLException {
		if(queries == null || parameters==null || queries.length != parameters.length) return; //throw exception
		for(int i=0; i<queries.length; i++)
			this.execute(queries[i], parameters[i]);
	}

	public abstract void preConnecion();
	public abstract void postConnecion();
	
	public abstract void preStatement();
	public abstract void postStatement();
	
	public abstract void preExec(SQL SQL) throws SQLException ;
	public abstract void postExec(SQL SQL, ResultSet rs) throws SQLException;
	
	protected Executor executorFor(Serializable... parameters) {
		return parameters==null || parameters.length==0 ? new StatmentExecutor() : new PreparedStatmentExecutor();
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
