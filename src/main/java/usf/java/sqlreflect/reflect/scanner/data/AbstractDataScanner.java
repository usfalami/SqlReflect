package usf.java.sqlreflect.reflect.scanner.data;

import java.sql.ResultSet;
import java.sql.Statement;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.binder.Binder;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
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

			ActionTimer action = at.startAction(Constants.ACTION_BINDING);
			stmt = getConnectionManager().prepare(query, args, binder);
			action.end(); //ACTION_STATEMENT end
			
			ResultSet rs = null;
			try {
			
				action = at.startAction(Constants.ACTION_EXECUTION);
				rs = getConnectionManager().executeQuery(stmt, query, args, binder);
				action.end(); //ACTION_EXECUTION end
				
				action = at.startAction(Constants.ACTION_PREPARATION);
				runPreparation(adapter, rs);
				action.end();
				
				action = at.startAction(Constants.ACTION_PROCESSING);
				runProcessing(rs, adapter, at);
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
		if(Utils.isNull(getMapper())) throw new IllegalArgumentException("Mapper can't be  is null");
		if(Utils.isEmptyString(query)) throw new IllegalArgumentException("Query can't be null");
		if(Utils.isIllegalArg(args, binder)) throw new IllegalArgumentException("Query parameters are not valid");
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
