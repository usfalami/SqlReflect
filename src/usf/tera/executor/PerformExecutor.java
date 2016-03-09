package usf.tera.executor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class PerformExecutor extends Executor {
	
	private long time;

	@Override
	protected void beforeExec(PreparedStatement s) {
		Date d = new Date();
		time = d.getTime();
		log("Query : start execution at " + DATE_FORMATTER.format(d));
	}

	@Override
	protected void afterExec(ResultSet rs) {
		Date d = new Date();
		time = d.getTime() - time;
		log("Query : end execution at " + DATE_FORMATTER.format(d));
		log(String.format("Elapsed time : %dms", time));
	}

}
