package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.PrimaryKeyMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.PrimaryKey;

public class PrimaryKeyScanner extends AbstractFieldScanner<PrimaryKey>{

	private String databasePattern, tablePattern;
	
	public PrimaryKeyScanner(ConnectionManager cm) {
		super(cm);
	}
	public PrimaryKeyScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<PrimaryKey> adapter, ActionTimer at) throws Exception {
		ResultSet rs = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
			rs = dm.getPrimaryKeys(null, databasePattern, tablePattern);
			action.end();
			
			Mapper<PrimaryKey> mapper = new PrimaryKeyMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = at.startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				PrimaryKey pk = mapper.map(rs, row+1);
				adapter.adapte(pk, row++);
			}
			action.end();
			
		}finally {
			getConnectionManager().close(rs);
		}
	}
	
	
	public PrimaryKeyScanner set(String databasePattern, String tablePattern) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		return this;
	}

}
