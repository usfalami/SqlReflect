package usf.java.sql.reflect.executor.adapter;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.SQL;
import usf.java.sql.formatter.Formatter;

public class MultiExecutorAdapter extends ExecutorAdapter {
	
	protected ExecutorAdapter[] adapters;

	public MultiExecutorAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void setAdapters(ExecutorAdapter... adapters) {
		this.adapters = adapters;
	}
	public ExecutorAdapter[] getAdapters() {
		return this.adapters;
	}
	
	@Override
	public void preConnecion() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.preConnecion();
	}
	@Override
	public void postConnecion() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.postConnecion();
	}
	@Override
	public void preStatement() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.preStatement();
	}
	@Override
	public void postStatement() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.postStatement();
	}
	@Override
	public void preExec(SQL sql) throws SQLException {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.preExec(sql);
	}
	@Override
	public void postExec(SQL sql, ResultSet rs) throws SQLException {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.postExec(sql, rs);
	}
	
}
