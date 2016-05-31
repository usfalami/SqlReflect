package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import usf.java.field.SQL;
import usf.java.formatter.AsciiFormatter;
import usf.java.formatter.Formatter;

public class ExecutorPerformAdapter implements ExecutorAdapter {

	private Formatter f;
	private Date start;
	
	public ExecutorPerformAdapter() {
		f = new AsciiFormatter(System.out, 4, PERFORM_TEXT_LENGTH);
	}
	
	@Override
	public void beforeExec(SQL sql) throws SQLException {
		start = new Date();
	}

	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
		Date end = new Date();
		synchronized(System.out) {
			f.startTable();
			f.formatTitle(sql.getName());
			f.formatHeaders("Start", "End", "Count", "Duration");
			int count=0;
			if(rs.next()){
				rs.last();
				count=rs.getRow();
				rs.beforeFirst();
			}
			f.formatRow(
					TIME_FORMATTER.format(start),
					TIME_FORMATTER.format(end),
					count+" rows",
					String.format("%dms",end.getTime()-start.getTime()));
			f.endTable();
		}
	}

}