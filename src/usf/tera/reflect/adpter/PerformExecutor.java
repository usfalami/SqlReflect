package usf.tera.reflect.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class PerformExecutor implements ExecutorAdapter {
	
	private long time;

	@Override
	public void beforeExec(PreparedStatement s) {
		Date d = new Date();
		time = d.getTime();
		System.out.println("Query : start execution at " + DATE_FORMATTER.format(d));
	}

	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		Date d = new Date();
		time = d.getTime() - time;
		System.out.println("Query : end execution at " + DATE_FORMATTER.format(d));
		System.out.println(String.format("Elapsed time : %dms", time));
		
		int count=0;
		
		if(rs.next()){
			rs.last();
			count=rs.getRow();
		    rs.beforeFirst();
		}
		
		System.out.println(count);
	}

}
