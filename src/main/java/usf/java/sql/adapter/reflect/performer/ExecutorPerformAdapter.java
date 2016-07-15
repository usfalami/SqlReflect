package usf.java.sql.adapter.reflect.performer;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Query;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.performer.ExecutorPerformer;

public class ExecutorPerformAdapter extends AbstractPerformerAdapter {

	protected Query callable;
	protected TimePerform time;
	
	public ExecutorPerformAdapter(SqlParser sqlParser) {
		super(sqlParser);
	}
	
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
	
	public void calc(ConnectionManager cm, String sql, Serializable... parameters) throws SQLException, AdapterException {
		this.callable = sqlParser.parseSQL(sql);
		new ExecutorPerformer(cm).run(this, callable, parameters);
	}
	
}