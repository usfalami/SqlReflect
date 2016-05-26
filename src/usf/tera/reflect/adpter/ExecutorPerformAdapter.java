package usf.tera.reflect.adpter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class ExecutorPerformAdapter implements ExecutorAdapter {
	
	private Date start;
	
	public static final String format="|%-10s | %-10s|\n";
	public static final String cadre=String.format("+%23s+\n", "").replace(" ", "-");

	@Override
	public void beforeExec(PreparedStatement s) {
		start = new Date();
	}

	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		Date end = new Date();
		System.out.print(cadre);
		System.out.format(format, "Start", TIME_FORMATTER.format(start));
		System.out.print(cadre);
		System.out.format(format, "End", TIME_FORMATTER.format(end));
		System.out.print(cadre);
		System.out.format(format, "Duration", (end.getTime() - start.getTime())+"ms");
		System.out.println(cadre);
	}

}
