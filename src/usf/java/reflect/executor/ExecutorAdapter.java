package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.field.SQL;
import usf.java.formatter.Formatter;
import usf.java.reflect.AbstractAdapter;

public abstract class ExecutorAdapter extends AbstractAdapter {
	
	protected SQL sql;
	protected Serializable[] parameters;
	
	public ExecutorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void execute(String query, Serializable... parameters) throws SQLException {
		this.sql = cm.parseSQL(query);
		this.parameters = parameters;
		Executor e = parameters.length==0 ? new StatmentExecutor() : new PreparedStatmentExecutor();
		e.run(this, query, parameters);
	}
	
	public void execute(int times, String sql, Serializable... parameters) throws SQLException {
		for(int i=0; i<times; i++) 
			execute(sql, parameters);
	}
	
	public abstract void beforeConnecion();
	public abstract void afterConnecion();
	
	public abstract void beforeStatement();
	public abstract void afterStatement();
	
	public abstract void beforeExec() throws SQLException ;
	public abstract void afterExec(ResultSet rs) throws SQLException;
	
	
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
