package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public class ExecutorPerformAdapter implements ExecutorAdapter {

	protected Formatter formatter;
	protected Date start;
	
	public ExecutorPerformAdapter(Formatter formatter) {
		this.formatter = formatter;
		formatter.configure(4, PERFORM_TEXT_LENGTH);
	}
	
	@Override
	public void beforeExec(SQL sql) throws SQLException {
		start = new Date();
	}

	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
		Date end = new Date();
		synchronized(System.out) {
			formatter.startTable();
			formatter.formatTitle(sql.getName());
			formatter.formatHeaders("Start", "End", "Count", "Duration");
			int count=0;
			if(rs.next()){
				rs.last();
				count=rs.getRow();
				rs.beforeFirst();
			}
			formatter.formatRow(
					TIME_FORMATTER.format(start),
					TIME_FORMATTER.format(end),
					count+" rows",
					String.format("%dms",end.getTime()-start.getTime()));
			formatter.endTable();
		}
	}

}