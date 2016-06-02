package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public class ExecutorResultAdapter implements ExecutorAdapter {

	protected Formatter formatter;
	
	public ExecutorResultAdapter(Formatter formatter) {
		this.formatter = formatter;
	}
	
	@Override
	public void beforeExec(SQL sql) {
		
	}

	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		formatter.configure(cols, VALUE_LENGTH);
		Object[] param = new Object[cols]; 
		for(int i=1; i<=cols; i++) param[i-1]=md.getColumnName(i);
		int count=Utils.rowsCount(rs);
		synchronized(System.out) {
			formatter.startTable();
			formatter.formatTitle(String.format("%s : %d row(s)", sql.getName(), count));
			formatter.formatHeaders(param);
			while(rs.next()){
				for(int i=1; i<=cols; i++) param[i-1]=rs.getObject(i);
				formatter.formatRow(param);
			}
			formatter.endTable();
		}
	}
	

	@Override
	public void beforeConnecion() {	}
	
	@Override
	public void afterConnecion() { }

	@Override
	public void beforeStatement() {	}
	
	@Override
	public void afterStatement() { }

}
