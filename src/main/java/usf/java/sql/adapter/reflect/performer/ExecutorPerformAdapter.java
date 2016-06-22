package usf.java.sql.adapter.reflect.performer;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.ReflectorUtils;

public class ExecutorPerformAdapter extends AbstractPerformerAdapter {

	protected long cnxStart, statStart, execStart, execEnd, statEnd, cnxEnd;
	
	public ExecutorPerformAdapter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, formatter);
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
	public void preExec(Callable sql) throws SQLException {
		execStart = System.currentTimeMillis();
	}
	@Override
	public void postExec(Callable sql, ResultSet rs) throws SQLException {
		execEnd = System.currentTimeMillis();
		int count = ReflectorUtils.rowsCount(rs);
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