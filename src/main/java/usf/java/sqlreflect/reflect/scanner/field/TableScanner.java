package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.TableMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.item.Column;
import usf.java.sqlreflect.sql.entry.item.Table;
import usf.java.sqlreflect.sql.type.TableTypes;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private boolean columns;
	private String[] types;
	
	public TableScanner(ConnectionManager cm) {
		super(cm, new TableMapper());
	}
	public TableScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new TableMapper());
	}

	@Override
	protected void runAdapt(ResultSet rs, Adapter<Table> adapter, ActionTimer at) throws Exception {
		if(!columns) super.runAdapt(rs, adapter, at);
		else {
			int row = 0;
			ColumnScanner as = new ColumnScanner(getConnectionManager()); 
			ListAdapter<Column> aa = new ListAdapter<Column>();
			while(rs.next()){
				Table t = getMapper().map(rs, row+1);
				as.set(t.getDatabaseName(), t.getName(), null).run(aa, at.createAction());	
				t.setColumns(aa.getList());
				adapter.adapte(t, row++);
			}
		}
	}
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getTables(dm, databasePattern, tablePattern, types);
	}
	
	public TableScanner set(String databasePattern, String tablePattern, boolean columns, TableTypes... types) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columns = columns;
		if(Utils.isEmptyArray(types)) 
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
