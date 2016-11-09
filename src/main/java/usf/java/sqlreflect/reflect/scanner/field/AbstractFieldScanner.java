package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.AbstractItemMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.scanner.AbstractScanner;

public abstract class AbstractFieldScanner<R> extends AbstractScanner<R> {
	
	public AbstractFieldScanner(ConnectionManager cm, AbstractItemMapper<R> mapper) {
		super(cm, mapper);
		mapper.setDatabaseType(getConnectionManager().getServer().getDatabaseType());
	}
	public AbstractFieldScanner(ConnectionManager cm, ActionTimer at, AbstractItemMapper<R> mapper) {
		super(cm, at, mapper);
		mapper.setDatabaseType(getConnectionManager().getServer().getDatabaseType());
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
		
		ResultSet rs = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
			rs = runExecution(dm);
			action.end(); //ACTION_EXECUTION end

			action = at.startAction(Constants.ACTION_ADAPT);
			getMapper().prepare(rs);
			adapter.prepare(getMapper());
			runAdapt(rs, adapter, at);
			action.end();
			
		}finally {
			getConnectionManager().close(rs);
		}
	}
	
	protected abstract ResultSet runExecution(DatabaseMetaData dm) throws Exception;

}
