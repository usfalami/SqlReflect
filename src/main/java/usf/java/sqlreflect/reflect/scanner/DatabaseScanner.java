package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.item.Database;
import usf.java.sqlreflect.mapper.DatabaseMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.ReflectorUtils;
import usf.java.sqlreflect.reflect.TimePerform;

public class DatabaseScanner extends AbstractFieldScanner<Database> {
	
	private String databasePattern;
	
	public DatabaseScanner(ConnectionManager cm) {
		super(cm);
	}
	
	public DatabaseScanner set(String databasePattern) {
		this.databasePattern = databasePattern;
		return this;
	}

	protected void run(DatabaseMetaData dm, Adapter<Database> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_EXECUTION);
			rs = ReflectorUtils.isEmpty(databasePattern) ? dm.getSchemas() : dm.getSchemas(null, databasePattern);
			action.end();
			
			Mapper<Database> mapper = new DatabaseMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = tp.startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Database database = mapper.map(rs, row+1);
				adapter.adapte(database, row++);
			}
			action.end();
			tp.setRowCount(row);
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
