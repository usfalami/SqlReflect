package usf.java.reflect.executor.adapter;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.formatter.Formatter;

public class ExecutorColumnAdapter extends ExecutorAdapter {

	public ExecutorColumnAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_CLASS_LENGTH);
	}
	
	@Override
	public void beforeExec() { }
	
	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
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
	
	@Override
	public void beforeConnecion() {	}
	
	@Override
	public void afterConnecion() { }
	
	@Override
	public void beforeStatement() {	}
	
	@Override
	public void afterStatement() { }
}
