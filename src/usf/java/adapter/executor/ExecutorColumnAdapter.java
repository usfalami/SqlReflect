package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public class ExecutorColumnAdapter implements ExecutorAdapter {

	protected Formatter formatter;
	
	public ExecutorColumnAdapter(Formatter formatter) {
		this.formatter = formatter;
		this.formatter.configure(COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_VALUE_TYPE_LENGTH, COLUMN_SIZE_LENGTH, COLUMN_CLASS_LENGTH);
	}
	
	@Override
	public void beforeExec(SQL sql) {
		
	}
	
	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
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
	public void beforeConnecion() {	}
	
	@Override
	public void afterConnecion() { }
	
	@Override
	public void beforeStatement() {	}
	
	@Override
	public void afterStatement() { }
}
