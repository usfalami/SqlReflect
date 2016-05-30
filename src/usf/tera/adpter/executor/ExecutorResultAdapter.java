package usf.tera.adpter.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.tera.field.SQL;
import usf.tera.formatter.AsciiFormatter;
import usf.tera.formatter.Formatter;

public class ExecutorResultAdapter implements ExecutorAdapter {

	private Formatter f;
	
	@Override
	public void beforeExec(SQL sql) {
		
	}

	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		f = new AsciiFormatter(System.out, count, VALUE_LENGTH);
		Object[] param = new Object[count]; 
		for(int i=1; i<=count; i++) param[i-1]=md.getColumnName(i);
		synchronized(System.out) {
			f.startTable();
			f.formatTitle(sql.getName());
			f.formatHeaders(param);
			while(rs.next()){
				for(int i=1; i<=count; i++) param[i-1]=rs.getObject(i);
				f.formatRow(param);
			}
			f.endTable();
		}
	}

}
