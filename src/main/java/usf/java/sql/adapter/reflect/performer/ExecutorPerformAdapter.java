package usf.java.sql.adapter.reflect.performer;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.ReflectorUtils;
import usf.java.sql.core.reflect.exception.AdapterException;

public class ExecutorPerformAdapter extends AbstractPerformerAdapter {

	protected TimePerform time;
	
	public ExecutorPerformAdapter(SqlParser sqlParser) {
		super(sqlParser);
	}
	
	@Override
	public void start() {
		time.setStart(System.currentTimeMillis());
	}

	@Override
	public void preConnecion() {	
		time.setCnxStart(System.currentTimeMillis());
	}
	@Override
	public void postConnecion() { 
		time.setCnxEnd(System.currentTimeMillis());
	}
	@Override
	public void preStatement() {
		time.setStatStart(System.currentTimeMillis());
	}
	@Override
	public void postStatement() {
		time.setStatEnd(System.currentTimeMillis());
	}

	@Override
	public void preExec(Callable sql) throws SQLException {
		time.setExecStart(System.currentTimeMillis());
	}
	@Override
	public void postExec(Callable sql, ResultSet rs) throws SQLException {
		time.setExecEnd(System.currentTimeMillis());
		time.setCount(ReflectorUtils.rowsCount(rs));
	}
	
	@Override
	public void end() throws AdapterException {
		time.setEnd(System.currentTimeMillis());
	}
	
	public TimePerform getTimePerform() {
		return time;
	}
	
}