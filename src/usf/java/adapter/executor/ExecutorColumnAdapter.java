package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.formatter.Formatter;
import usf.java.reflect.executor.ExecutorAdapter;

public class ExecutorColumnAdapter extends ExecutorAdapter {

	public ExecutorColumnAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_CLASS_LENGTH);
	}
	
	@Override
	protected void beforeExec() {
		
	}
	
	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		synchronized(formatter.getOut()) {
			formatter.startTable();
			formatter.formatTitle(sql.getName());
			formatter.formatHeaders("N°", "Name", "Type", "Size", "Class"); 
			for(int i=1; i<=count; i++)
				formatter.formatRow(i,
						md.getColumnName(i),
						md.getColumnTypeName(i),
						md.getColumnDisplaySize(i),
						md.getColumnClassName(i));
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
