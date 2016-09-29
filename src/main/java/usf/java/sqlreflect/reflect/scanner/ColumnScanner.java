package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ColumnMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Column;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	public ColumnScanner(ConnectionManager cm) {
		super(cm);
	}
	public ColumnScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Column> adapter, String arg1, String arg2, String arg3) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getColumns(null, arg1, arg2, arg3);
			action.end();
			
			Mapper<Column> mapper = new ColumnMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Column column = mapper.map(rs, row+1);
				adapter.adapte(column, row++);
			}
			action.end();
			getTimePerform().setRowCount(row);
			
		}finally {
			getConnectionManager().close(rs);
		}
	}

	public final List<Column> run(String databasePattern, String tablePattern, String columnPattern) throws Exception {
		ListAdapter<Column> adapter = new ListAdapter<Column>();
		super.run(adapter, databasePattern, tablePattern, columnPattern);
		return adapter.getList();
	}
	public void run(Adapter<Column> adapter, String databasePattern, String tablePattern, String columnPattern) throws Exception {
		super.run(adapter, databasePattern, tablePattern, columnPattern);
	}
	
}
