package usf.java.sql.core.adapter;

import java.sql.SQLException;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.reflect.performer.Performer.PerformAdapter;

public class TimePerformAdapter implements PerformAdapter {

	protected TimePerform time;
	
	@Override
	public void start() {
		time = new TimePerform();
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
		time.setRowsCount(rowCount);
	}
	
	@Override
	public void end() throws AdapterException {
		time.setEnd(System.currentTimeMillis());
	}
	
	public TimePerform getTimePerform() {
		return time;
	}
	
}