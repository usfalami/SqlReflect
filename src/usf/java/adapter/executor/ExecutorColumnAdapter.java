package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.field.SQL;
import usf.java.formatter.AsciiFormatter;
import usf.java.formatter.Formatter;

public class ExecutorColumnAdapter implements ExecutorAdapter {

	private Formatter f;
	
	public ExecutorColumnAdapter() {
		f = new AsciiFormatter(System.out, COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH);
	}
	
	@Override
	public void beforeExec(SQL sql) {
		
	}
	
	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		int count = md.getColumnCount();
		synchronized(System.out) {
			f.startTable();
			f.formatTitle(sql.getName());
			f.formatHeaders("N°", "Name", "Type", "Size"); 
			for(int i=1; i<=count; i++)
				f.formatRow(i,
						md.getColumnName(i),
						md.getColumnTypeName(i),
						md.getColumnDisplaySize(i));
			f.endTable();
		}
	}
}
