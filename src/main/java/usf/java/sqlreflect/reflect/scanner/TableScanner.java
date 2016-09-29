package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.TableMapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Column;
import usf.java.sqlreflect.sql.item.Table;
import usf.java.sqlreflect.sql.type.TableTypes;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private boolean columns;
	private String[] types = {TableTypes.TABLE.toString()};
	
	public TableScanner(ConnectionManager cm) {
		super(cm);
	}
	public TableScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	public TableScanner set(boolean columns, TableTypes... types) {
		this.columns = columns;
		if(Utils.isEmpty(types)) 
			this.types = new String[]{TableTypes.TABLE.toString()};
		else{
			this.types = new String[types.length];
			for(int i=0; i<types.length; i++)
				this.types[i] = types[i].toString();
		}
		return this;
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Table> adapter, String arg1, String arg2, String arg3) throws Exception {
		ResultSet rs = null;
		try {
			
			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getTables(null, arg1, arg2, types); //TODO : check types == null
			action.end();
			
			Mapper<Table> mapper = new TableMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			if(columns) { // look for columns
				ColumnScanner cs = new ColumnScanner(getConnectionManager(), getTimePerform());
				ListAdapter<Column> ca = new ListAdapter<Column>();
				while(rs.next()){
					Table t = mapper.map(rs, row+1);
					cs.runScan(dm, ca, t.getDatabaseName(), t.getName(), null);
					t.setColumns(ca.getList());
					adapter.adapte(t, row++);
				}
			}
			else
			{
				while(rs.next()){
					Table t = mapper.map(rs, row+1);
					adapter.adapte(t, row++);
				}
			}
			action.end();
			getTimePerform().setRowCount(row);
			
		}finally {
			getConnectionManager().close(rs);
		}
	}
	
	public final List<Table> run(String databasePattern, String tablePattern) throws Exception {
		ListAdapter<Table> adapter = new ListAdapter<Table>();
		super.run(adapter, databasePattern, tablePattern,  null);
		return adapter.getList();
	}
	public void run(Adapter<Table> adapter, String databasePattern, String tablePattern) throws Exception {
		super.run(adapter, databasePattern, tablePattern, null);
	}
}
