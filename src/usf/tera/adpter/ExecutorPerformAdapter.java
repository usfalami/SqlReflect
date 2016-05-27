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
		synchronized(System.out) {
			System.out.print(PERFORM_CADRE);
			System.out.format(PERFORM_FORMAT, "Start", TIME_FORMATTER.format(start));
			System.out.print(PERFORM_CADRE);
			System.out.format(PERFORM_FORMAT, "End", TIME_FORMATTER.format(end));
			System.out.print(PERFORM_CADRE);
			int count=0;
			if(rs.next()){
				rs.last();
				count=rs.getRow();
				rs.beforeFirst();
			}
			System.out.format(PERFORM_FORMAT, "Count", count+" rows");
			System.out.print(PERFORM_CADRE);
			System.out.format(PERFORM_FORMAT, "Duration", (end.getTime() - start.getTime())+" ms");
			System.out.println(PERFORM_CADRE);
		}
	}

}