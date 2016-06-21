package usf.java.sql.adapter.reflect.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.parser.SqlParser;

public class ExecutorResultColumnAdapter extends AbstractExecutorAdapter {

	public ExecutorResultColumnAdapter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_CLASS_LENGTH);
	}
	
	@Override
	public void preExec(Callable sql) { }
	
	@Override
	public void postExec(Callable sql, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		formatter.startTable();
		formatter.formatTitle(sql.getName());
		formatter.formatHeaders("N°", "Name", "Type", "Size", "Class"); 
		formatter.startRows();
		for(int i=1; i<=count; i++)
			formatter.formatRow(i,
				md.getColumnName(i),
				md.getColumnTypeName(i),
				md.getColumnDisplaySize(i),
				md.getColumnClassName(i));
		formatter.endRows();
		formatter.endTable();
	}
	
	@Override
	public void preConnecion() 	{ }
	
	@Override
	public void postConnecion() { }
	
	@Override
	public void preStatement() 	{ }
	
	@Override
	public void postStatement() { }
}
