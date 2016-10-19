package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.AbstractReflector;
import usf.java.sqlreflect.reflect.ActionTimer;

public abstract class AbstractFieldScanner<R> extends AbstractReflector<ConnectionManager, R> implements Scanner {
	
	private Mapper<R> mapper;
	
	public AbstractFieldScanner(ConnectionManager cm, Mapper<R> mapper) {
		super(cm);
		this.mapper = mapper;
	}
	public AbstractFieldScanner(ConnectionManager cm, ActionTimer at, Mapper<R> mapper) {
		super(cm, at);
		this.mapper = mapper;
	}
	
	@Override
	public void run(Adapter<R> adapter, ActionTimer at) throws Exception {
		DatabaseMetaData dm = getConnectionManager().getConnection().getMetaData();
		
		ResultSet rs = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
			rs = runScan(dm);
			action.end();
			
			adapter.prepare(mapper);
			int row = 0;

			action = at.startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				R field = mapper.map(rs, row+1);
				adapter.adapte(field, row++);
			}
			action.end();
			
		}finally {
			getConnectionManager().close(rs);
		}
	}

	protected abstract ResultSet runScan(DatabaseMetaData dm) throws Exception;
}
