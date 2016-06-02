package usf.java.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public class ExecutorPerformAdapter implements ExecutorAdapter {

	protected Formatter formatter;
	protected Date cnxStart, statStart, execStart, execEnd, statEnd, cnxEnd;
	
	public ExecutorPerformAdapter(Formatter formatter) {
		this.formatter = formatter;
		formatter.configure(4, PERFORM_TEXT_LENGTH);
	}

	@Override
	public void beforeConnecion() {	
		cnxStart = new Date();
	}
	@Override
	public void afterConnecion() { 
		cnxEnd = new Date();
	}
	@Override
	public void beforeStatement() {
		statStart = new Date();
	}
	@Override
	public void afterStatement() {
		statEnd = new Date();
	}

	@Override
	public void beforeExec(SQL sql) throws SQLException {
		execStart = new Date();
	}
	@Override
	public void afterExec(SQL sql, ResultSet rs) throws SQLException {
		execEnd = new Date();
		int count=Utils.rowsCount(rs);
		synchronized(System.out) {
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