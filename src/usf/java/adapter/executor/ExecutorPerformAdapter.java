package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import usf.java.connection.ConnectionManager;
import usf.java.formatter.Formatter;
import usf.java.reflect.executor.ExecutorAdapter;

public class ExecutorPerformAdapter extends ExecutorAdapter {

	protected Date cnxStart, statStart, execStart, execEnd, statEnd, cnxEnd;
	
	public ExecutorPerformAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		formatter.configure(4, PERFORM_TEXT_LENGTH);
	}

	@Override
	protected void beforeConnecion() {	
		cnxStart = new Date();
	}
	@Override
	protected void afterConnecion() { 
		cnxEnd = new Date();
	}
	@Override
	protected void beforeStatement() {
		statStart = new Date();
	}
	@Override
	protected void afterStatement() {
		statEnd = new Date();
	}

	@Override
	protected void beforeExec() throws SQLException {
		execStart = new Date();
	}
	@Override
	protected void afterExec(ResultSet rs) throws SQLException {
		execEnd = new Date();
		int count=rowsCount(rs);
		synchronized(formatter.getOut()) {
			formatter.startTable();
			formatter.formatTitle(String.format("%s : %d row(s)", sql.getName(), count));
			formatter.formatHeaders("Action", "Start", "End", "Duration");
			formatter.formatRow("Connection",
				TIME_FORMATTER.format(cnxStart),
				TIME_FORMATTER.format(cnxEnd),
				String.format("%5d ms",cnxEnd.getTime()-cnxStart.getTime()));
			formatter.formatRow("Statment",
				TIME_FORMATTER.format(statStart),
				TIME_FORMATTER.format(statEnd),
				String.format("%5d ms",statEnd.getTime()-statStart.getTime()));
			formatter.formatRow("Execution",
				TIME_FORMATTER.format(execStart),
				TIME_FORMATTER.format(execEnd),
				String.format("%5d ms",execEnd.getTime()-execStart.getTime()));
			formatter.formatRow("Total",
				TIME_FORMATTER.format(cnxStart),
				TIME_FORMATTER.format(execEnd),
				String.format("%5d ms",execEnd.getTime()-cnxStart.getTime()));
			formatter.endTable();
		}
	}

}