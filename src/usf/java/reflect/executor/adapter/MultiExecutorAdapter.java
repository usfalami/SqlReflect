package usf.java.reflect.executor.adapter;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.formatter.Formatter;
import usf.java.reflect.executor.Executor;
import usf.java.reflect.executor.PreparedStatmentExecutor;
import usf.java.reflect.executor.StatmentExecutor;

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
	public void beforeConnecion() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.beforeConnecion();
	}
	@Override
	public void afterConnecion() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.afterConnecion();
	}
	@Override
	public void beforeStatement() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.beforeStatement();
	}
	@Override
	public void afterStatement() {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.afterStatement();
	}
	@Override
	public void beforeExec() throws SQLException {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.beforeExec();
	}
	@Override
	public void afterExec(ResultSet rs) throws SQLException {
		if(adapters==null) return;
		for(ExecutorAdapter a : adapters)
			a.afterExec(rs);
	}
	
	public void execute(String query, Serializable... parameters) throws SQLException {
		this.sql = cm.parseSQL(query);
		this.parameters = parameters;
		Executor e = parameters==null || parameters.length==0 ? new StatmentExecutor() : new PreparedStatmentExecutor();
		for(ExecutorAdapter a : adapters)
			a.set(sql, parameters);
		e.run(this, query, parameters);
	}
}
