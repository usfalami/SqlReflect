package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.field.Table;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.TableMapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private boolean columns;
	private TableType type;
	
	public TableScanner(ConnectionManager cm) {
		this(cm, TableType.TABLE);
	}
	
	public TableScanner(ConnectionManager cm ,TableType type) {
		super(cm);
		this.type = type;
	}
	
	public TableScanner set(String databasePattern, String tablePattern, boolean columns) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columns = columns;
		return this;
	}

	@Override
	protected void run(DatabaseMetaData dm, Adapter<Table> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {
			
			ActionPerform action = tp.startAction(Constants.ACTION_EXECUTION);
			rs = dm.getTables(null, databasePattern, tablePattern, new String[]{type.toString()});
			action.end();
			
			Mapper<Table> mapper = new TableMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = tp.startAction(Constants.ACTION_ADAPT);
			if(columns) { // look for columns
				ColumnScanner ts = new ColumnScanner(getConnectionManager(), HasColumn.TABLE);
				while(rs.next()){
					Table t = mapper.map(rs, row+1);
					List<Column> columns = ts.set(t.getDatabase(), t.getName(), null).run();
					t.setColumns(columns);
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
			tp.setRowCount(row);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}
	
}
