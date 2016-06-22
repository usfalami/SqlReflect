package usf.java.sql.adapter.reflect.performer;

import java.sql.SQLException;

import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.parser.SqlParser;
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
	public void preExec() throws SQLException {
		time.setExecStart(System.currentTimeMillis());
	}
	@Override
	public void postExec(int rowCount) throws SQLException {
		time.setExecEnd(System.currentTimeMillis());
		time.setCount(rowCount);
	}
	
	@Override
	public void end() throws AdapterException {
		time.setEnd(System.currentTimeMillis());
	}
	
	public TimePerform getTimePerform() {
		return time;
	}
	
}