package usf.java.sql.adapter.reflect.performer;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.Utils;

public class ExecutorResultSetAdapter extends AbstractExecutorAdapter {
	
	public ExecutorResultSetAdapter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, formatter);
	}
	
	@Override
	public void preExec(Callable sql) {
		
	}

	@Override
	public void postExec(Callable sql, ResultSet rs) throws SQLException {
		int cols = Utils.columnsCount(rs);
		formatter.configureAll(cols, VALUE_LENGTH);
		int count = Utils.rowsCount(rs);
		formatter.startTable();
		formatter.formatTitle(String.format("%s : %d row(s)", sql.getName(), count));
		formatter.formatHeaders((Object[])Utils.columnNames(rs));
		formatter.startRows();
		Object[] param = new Object[cols]; 
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
