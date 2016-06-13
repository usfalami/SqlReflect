package usf.java.sql.reflect.adapter.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.SQL;
import usf.java.sql.formatter.Formatter;

public class ExecutorPerformAdapter extends AbstractExecutorAdapter {

	protected long cnxStart, statStart, execStart, execEnd, statEnd, cnxEnd;
	
	public ExecutorPerformAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		formatter.configureAll(4, PERFORM_TEXT_LENGTH);
	}

	@Override
	public void preConnecion() {	
		cnxStart = System.currentTimeMillis();
	}
	@Override
	public void postConnecion() { 
		cnxEnd = System.currentTimeMillis();
	}
	@Override
	public void preStatement() {
		statStart = System.currentTimeMillis();
	}
	@Override
	public void postStatement() {
		statEnd = System.currentTimeMillis();
	}

	@Override
	public void preExec(SQL sql) throws SQLException {
		execStart = System.currentTimeMillis();
	}
	@Override
	public void postExec(SQL sql, ResultSet rs) throws SQLException {
		execEnd = System.currentTimeMillis();
		int count=rowsCount(rs);
		formatter.startTable();
		formatter.formatTitle(String.format("%s : %d row(s)", sql.getName(), count));
		formatter.formatHeaders("Action", "Start", "End", "Duration");
		formatter.startRows();
		formatter.formatRow("Connection",
			TIME_FORMATTER.format(cnxStart),
			TIME_FORMATTER.format(cnxEnd),
			String.format("%5d ms",cnxEnd-cnxStart));
		formatter.formatRow("Statment",
			TIME_FORMATTER.format(statStart),
			TIME_FORMATTER.format(statEnd),
			String.format("%5d ms",statEnd-statStart));
		formatter.formatRow("Execution",
			TIME_FORMATTER.format(execStart),
			TIME_FORMATTER.format(execEnd),
			String.format("%5d ms",execEnd-execStart));
		formatter.formatRow("Total",
			TIME_FORMATTER.format(cnxStart),
			TIME_FORMATTER.format(execEnd),
			String.format("%5d ms",execEnd-cnxStart));
		formatter.endRows();
		formatter.endTable();
	}

}