package usf.java.reflect.executor;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.adapter.Adapter;
import usf.java.field.SQL;
import usf.java.formatter.Formatter;
import usf.java.reflect.ReflectFactory;

public abstract class ExecutorAdapter extends Adapter {
	
	private SQL sql;
	private Serializable[] parameters;
	
	public ExecutorAdapter(ReflectFactory rf, Formatter formatter) {
		super(rf, formatter);
	}
	public SQL getSQL() {
		return sql;
	}
	public Serializable[] getParametters() {
		return parameters;
	}
	
	public void execute(String sql, Serializable... parameters) throws SQLException {
		this.sql = rf.parseSQL(sql);
		this.parameters = parameters;
		new Executor().run(this);
	}
	
	
	protected abstract void beforeConnecion();
	protected abstract void afterConnecion();
	
	protected abstract void beforeStatement();
	protected abstract void afterStatement();
	
	protected abstract void beforeExec(SQL sql) throws SQLException ;
	protected abstract void afterExec(SQL sql, ResultSet rs) throws SQLException;
	
	
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
