package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.TableMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Column;
import usf.java.sqlreflect.sql.item.Table;
import usf.java.sqlreflect.sql.type.TableTypes;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private boolean columns;
	private String[] types;
	
	public TableScanner(ConnectionManager cm) {
		super(cm);
	}
	public TableScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Table> adapter) throws Exception {
		ResultSet rs = null;
		try {
			
			ActionTimer action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getTables(null, databasePattern, tablePattern, types);
			action.end();
			
			Mapper<Table> mapper = new TableMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			if(columns) { // look for columns
				ColumnScanner cs = new ColumnScanner(getConnectionManager());
				ListAdapter<Column> ca = new ListAdapter<Column>();
				while(rs.next()){
					Table t = mapper.map(rs, row+1);
					cs.set(t.getDatabaseName(), t.getName(), null).runScan(dm, ca);
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
	
	public TableScanner set(String databasePattern, String tablePattern, boolean columns, TableTypes... types) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
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
	public TableScanner set(String databasePattern, String tablePattern, boolean columns) {
		return set(databasePattern, tablePattern, columns, TableTypes.TABLE);
	}
	public TableScanner set(String databasePattern, String tablePattern) {
		return set(databasePattern, tablePattern, false, TableTypes.TABLE);
	}
}
