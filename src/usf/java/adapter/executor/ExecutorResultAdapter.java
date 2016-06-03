package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.formatter.Formatter;
import usf.java.reflect.executor.ExecutorAdapter;

public class ExecutorResultAdapter extends ExecutorAdapter {
	
	public ExecutorResultAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
	}
	
	@Override
	protected void beforeExec() {
		
	}

	@Override
	protected void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		formatter.configure(cols, VALUE_LENGTH);
		Object[] param = new Object[cols]; 
		for(int i=1; i<=cols; i++) param[i-1]=md.getColumnName(i);
		int count=rowsCount(rs);
		synchronized(formatter.getOut()) {
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
	protected void beforeConnecion() {	}
	
	@Override
	protected void afterConnecion() { }

	@Override
	protected void beforeStatement() {	}
	
	@Override
	protected void afterStatement() { }

}
