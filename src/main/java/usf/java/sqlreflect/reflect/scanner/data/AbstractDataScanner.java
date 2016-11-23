package usf.java.sqlreflect.reflect.scanner.data;

import java.sql.ResultSet;
import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.reflect.scanner.AbstractScanner;

public abstract class AbstractDataScanner<A, R> extends AbstractScanner<R> {
	
	private String query;
	private Binder<A> binder;
	private A args;

	public AbstractDataScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm, mapper);
	}
	public AbstractDataScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at, mapper);
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		Statement stmt = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_PREPARATION);
			stmt = getConnectionManager().prepare(query, args, binder);
			action.end(); //ACTION_STATEMENT end
			
			ResultSet rs = null;
			try {
			
				action = at.startAction(Constants.ACTION_EXECUTION);
				rs = getConnectionManager().executeQuery(stmt, query, args, binder);
				action.end(); //ACTION_EXECUTION end
				
				action = at.startAction(Constants.ACTION_PROCESSING);
				getMapper().prepare(rs);
				adapter.prepare(getMapper());
				runAdapt(rs, adapter, at);
				action.end();
			
			}finally {
				getConnectionManager().close(rs);
			}
		}finally {
			getConnectionManager().close(stmt);
		}
	}
	
	@Override
	protected void validateArgs() {
		super.validateArgs();
		if(Utils.isEmptyString(query)) throw new IllegalArgumentException("Query can't be null");
		if(getMapper() == null) throw new IllegalArgumentException("Mapper can't be  is null");
	}
	
	public AbstractDataScanner<A, R> set(String query, A args, Binder<A> binder) {
		this.query = query;
		this.binder = binder;
		this.args = args;
		return this;
	}
	public AbstractDataScanner<A, R> set(String query) {
		return set(query, null, null);
	}
}
