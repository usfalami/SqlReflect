package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ArgumentMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Argument;

public class ArgumentScanner extends AbstractFieldScanner<Argument> {
	
	public ArgumentScanner(ConnectionManager cm) {
		super(cm);
	}
	public ArgumentScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Argument> adapter, String arg1, String arg2, String arg3) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getProcedureColumns(null, arg1, arg2, arg3);
			action.end();
			
			Mapper<Argument> mapper = new ArgumentMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Argument argument = mapper.map(rs, row+1);
				adapter.adapte(argument, row++);
			}
			action.end();
			getTimePerform().setRowCount(row);
			
		}finally {
			getConnectionManager().close(rs);
		}
	}

	public final List<Argument> run(String databasePattern, String tablePattern, String argumentPattern) throws Exception {
		ListAdapter<Argument> adapter = new ListAdapter<Argument>();
		super.run(adapter, databasePattern, tablePattern, argumentPattern);
		return adapter.getList();
	}
	public void run(Adapter<Argument> adapter, String databasePattern, String tablePattern, String argumentPattern) throws Exception {
		super.run(adapter, databasePattern, tablePattern, argumentPattern);
	}

}
