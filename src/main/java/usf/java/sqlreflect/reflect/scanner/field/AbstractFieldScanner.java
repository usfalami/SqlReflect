package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.scanner.AbstractScanner;

public abstract class AbstractFieldScanner<R> extends AbstractScanner<R> {
	
	public AbstractFieldScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm, mapper);
	}
	public AbstractFieldScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at, mapper);
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
		
		ResultSet rs = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
			rs = runExecution(dm);
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
	}
	
	@Override
	protected void validateArgs() {
		super.validateArgs();
		if(Utils.isNull(getMapper())) throw new IllegalArgumentException("Mapper can't be  is null");
	}
	
	protected abstract ResultSet runExecution(DatabaseMetaData dm) throws Exception;

}
