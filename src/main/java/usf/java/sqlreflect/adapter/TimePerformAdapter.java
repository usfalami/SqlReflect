package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.performer.TimePerform;

public class TimePerformAdapter implements PerformAdapter {

	protected TimePerform time;
	
	@Override
	public void start() {
		time = new TimePerform();
		time.start();
	}

	@Override
	public void preConnecion() {	
		time.cnxStart();
	}
	@Override
	public void postConnecion() { 
		time.cnxEnd();
	}
	@Override
	public void preStatement() {
		time.statStart();
	}
	@Override
	public void postStatement() {
		time.statEnd();
	}

	@Override
	public void preExec() {
		time.execStart();
	}
	@Override
	public void postExec(int rowCount) {
		time.execEnd();
		time.setRowCount(rowCount);
	}
	
	@Override
	public void end() throws Exception {
		time.end();
	}
	
	public TimePerform getTimePerform() {
		return time;
	}
	
}