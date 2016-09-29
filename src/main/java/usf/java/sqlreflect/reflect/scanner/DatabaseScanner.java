package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.DatabaseMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Argument;
import usf.java.sqlreflect.sql.item.Database;

public class DatabaseScanner extends AbstractFieldScanner<Database> {
	
	public DatabaseScanner(ConnectionManager cm) {
		super(cm);
	}
	public DatabaseScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Database> adapter, String arg1, String arg2, String arg3) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = Utils.isEmpty(arg1) ? dm.getSchemas() : dm.getSchemas(null, arg1);
			action.end();
			
			Mapper<Database> mapper = new DatabaseMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Database database = mapper.map(rs, row+1);
				adapter.adapte(database, row++);
			}
			action.end();
			getTimePerform().setRowCount(row);
		}finally {
			getConnectionManager().close(rs);
		}
	}
	
	public final List<Database> run(String databasePattern) throws Exception {
		ListAdapter<Database> adapter = new ListAdapter<Database>();
		super.run(adapter, databasePattern, null, null);
		return adapter.getList();
	}
	public void run(Adapter<Database> adapter, String databasePattern) throws Exception {
		super.run(adapter, databasePattern, null, null);
	}

}
