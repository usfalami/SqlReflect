package usf.java.sql.adapter.reflect.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.parser.SqlParser;

public class ExecutorResultSetAdapter extends AbstractExecutorAdapter {
	
	public ExecutorResultSetAdapter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, formatter);
	}
	
	@Override
	public void preExec(Callable sql) {
		
	}

	@Override
	public void postExec(Callable sql, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int cols = md.getColumnCount();
		formatter.configureAll(cols, VALUE_LENGTH);
		Object[] param = new Object[cols]; 
		for(int i=1; i<=cols; i++) param[i-1]=md.getColumnName(i);
		int count = Utils.rowsCount(rs);
		formatter.startTable();
		formatter.formatTitle(String.format("%s : %d row(s)", sql.getName(), count));
		formatter.formatHeaders(param);
		formatter.startRows();
		while(rs.next()){
			for(int i=1; i<=cols; i++) param[i-1]=rs.getObject(i);
			formatter.formatRow(param);
		}
		formatter.endRows();
		formatter.endTable();
	}
	

	@Override
	public void preConnecion() { }
	
	@Override
	public void postConnecion() { }

	@Override
	public void preStatement() { }
	
	@Override
	public void postStatement() { }

}
