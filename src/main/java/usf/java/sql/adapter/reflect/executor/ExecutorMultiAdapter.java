package usf.java.sql.adapter.reflect.executor;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.db.field.SQL;

public class ExecutorMultiAdapter extends AbstractExecutorAdapter {
	
	protected AbstractExecutorAdapter[] adapters;

	public ExecutorMultiAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void setAdapters(AbstractExecutorAdapter... adapters) {
		this.adapters = adapters;
	}
	public AbstractExecutorAdapter[] getAdapters() {
		return this.adapters;
	}
	
	@Override
	public void preConnecion() {
		if(adapters==null) return;
		for(AbstractExecutorAdapter a : adapters)
			a.preConnecion();
	}
	@Override
	public void postConnecion() {
		if(adapters==null) return;
		for(AbstractExecutorAdapter a : adapters)
			a.postConnecion();
	}
	@Override
	public void preStatement() {
		if(adapters==null) return;
		for(AbstractExecutorAdapter a : adapters)
			a.preStatement();
	}
	@Override
	public void postStatement() {
		if(adapters==null) return;
		for(AbstractExecutorAdapter a : adapters)
			a.postStatement();
	}
	@Override
	public void preExec(SQL sql) throws SQLException {
		if(adapters==null) return;
		for(AbstractExecutorAdapter a : adapters)
			a.preExec(sql);
	}
	@Override
	public void postExec(SQL sql, ResultSet rs) throws SQLException {
		if(adapters==null) return;
		for(AbstractExecutorAdapter a : adapters)
			a.postExec(sql, rs);
	}
	
}
