package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.adapter.AbstractAdapter;
import usf.java.connection.ConnectionManager;
import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public abstract class ExecutorAdapter extends AbstractAdapter {
	
	protected SQL sql;
	protected Serializable[] parameters;
	
	public ExecutorAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
	}
	public String getSQL() {
		return sql.getSql();
	}
	public Serializable[] getParametters() {
		return parameters;
	}
	
	public void execute(String sql, Serializable... parameters) throws SQLException {
		this.sql = rf.parseSQL(sql);
		this.parameters = parameters;
		(parameters == null ? new StatmentExecutor() : new PreparedStatmentExecutor()).run(this);
	}
	
	
	protected abstract void beforeConnecion();
	protected abstract void afterConnecion();
	
	protected abstract void beforeStatement();
	protected abstract void afterStatement();
	
	protected abstract void beforeExec() throws SQLException ;
	protected abstract void afterExec(ResultSet rs) throws SQLException;
	
	
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
