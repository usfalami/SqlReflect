package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
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
			action.end();

			action = at.startAction(Constants.ACTION_ADAPT);
			adapter.prepare(getMapper());
			runAdapt(rs, adapter, at);
			action.end();
			
		}finally {
			getConnectionManager().close(rs);
		}
	}
	
	protected void runAdapt(ResultSet rs, Adapter<R> adapter, ActionTimer at) throws Exception {
		int row = 0;
		while(rs.next()){
			R field = getMapper().map(rs, row+1);
			adapter.adapte(field, row++);
		}
	}

	protected abstract ResultSet runExecution(DatabaseMetaData dm) throws Exception;

}
