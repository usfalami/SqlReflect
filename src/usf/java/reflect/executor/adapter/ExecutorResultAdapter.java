package usf.java.reflect.executor.adapter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public class ExecutorResultAdapter extends ExecutorAdapter {
	
	public ExecutorResultAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	@Override
	public void preExec(SQL sql) {
		
	}

	@Override
	public void postExec(SQL sql, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		formatter.configure(cols, VALUE_LENGTH);
		Object[] param = new Object[cols]; 
		for(int i=1; i<=cols; i++) param[i-1]=md.getColumnName(i);
		int count=rowsCount(rs);
		formatter.startTable();
		formatter.formatTitle(String.format("%s : %d row(s)", sql.getName(), count));
		formatter.formatHeaders(param);
		while(rs.next()){
			for(int i=1; i<=cols; i++) param[i-1]=rs.getObject(i);
			formatter.formatRow(param);
		}
		formatter.endTable();
	}
	

	@Override
	public void preConnecion() {	}
	
	@Override
	public void postConnecion() { }

	@Override
	public void preStatement() {	}
	
	@Override
	public void postStatement() { }

}
