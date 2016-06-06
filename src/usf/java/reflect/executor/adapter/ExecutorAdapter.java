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
	
	protected SQL sql;
	protected Serializable[] parameters;
	
	public ExecutorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void execute(String query, Serializable... parameters) throws SQLException {
		Executor e = parameters.length==0 ? new StatmentExecutor() : new PreparedStatmentExecutor();
		this.sql = cm.parseSQL(query);
		this.parameters = parameters;
		e.run(this, query, parameters);
	}
	
	public void execute(int times, String query, Serializable... parameters) throws SQLException {
		Executor e = parameters.length==0 ? new StatmentExecutor() : new PreparedStatmentExecutor();
		this.sql = cm.parseSQL(query);
		this.parameters = parameters;
		for(int i=0; i<times; i++) 
			e.run(this, query, parameters);
	}
	
	public void execute(String... queries) throws SQLException {
		if(queries == null) return;
		Executor e = new StatmentExecutor();
		for(String query : queries) {
			this.sql = cm.parseSQL(query);
			e.run(this, query);
		}
	}
	
	public abstract void preConnecion();
	public abstract void postConnecion();
	
	public abstract void preStatement();
	public abstract void postStatement();
	
	public abstract void preExec() throws SQLException ;
	public abstract void postExec(ResultSet rs) throws SQLException;
	
	
	public static int rowsCount(ResultSet rs) throws SQLException{
		int count = 0;
		if(rs.next()){
			rs.last();
			count=rs.getRow();
			rs.beforeFirst();
		}
		return count;
	}

	public void set(SQL sql, Serializable... parameters) {
		this.sql = sql;
		this.parameters = parameters;
	}

}
