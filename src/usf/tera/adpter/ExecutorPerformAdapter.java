package usf.tera.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ExecutorPerformAdapter implements ExecutorAdapter {
	
	private Date start;

	@Override
	public void beforeExec(PreparedStatement s) throws SQLException {
		start = new Date();
	}

	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		Date end = new Date();
		Formatter f = new Formatter(System.out, 4, PERFORM_TEXT_LENGTH);
		synchronized(System.out) {
			f.startTable();
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