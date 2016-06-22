package usf.java.sql.adapter.reflect.performer;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.parser.SqlParser;

public class ExecutorMultiAdapter extends AbstractPerformerAdapter {
	
	protected AbstractPerformerAdapter[] adapters;

	public ExecutorMultiAdapter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, formatter);
	}
	
	public void setAdapters(AbstractPerformerAdapter... adapters) {
		this.adapters = adapters;
	}
	public AbstractPerformerAdapter[] getAdapters() {
		return this.adapters;
	}
	
	@Override
	public void preConnecion() {
		if(adapters==null) return;
		for(AbstractPerformerAdapter a : adapters)
			a.preConnecion();
	}
	@Override
	public void postConnecion() {
		if(adapters==null) return;
		for(AbstractPerformerAdapter a : adapters)
			a.postConnecion();
	}
	@Override
	public void preStatement() {
		if(adapters==null) return;
		for(AbstractPerformerAdapter a : adapters)
			a.preStatement();
	}
	@Override
	public void postStatement() {
		if(adapters==null) return;
		for(AbstractPerformerAdapter a : adapters)
			a.postStatement();
	}
	@Override
	public void preExec(Callable sql) throws SQLException {
		if(adapters==null) return;
		for(AbstractPerformerAdapter a : adapters)
			a.preExec(sql);
	}
	@Override
	public void postExec(Callable sql, ResultSet rs) throws SQLException {
		if(adapters==null) return;
		for(AbstractPerformerAdapter a : adapters)
			a.postExec(sql, rs);
	}
	
}
