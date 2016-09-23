package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.TableMapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.TableTypes;
import usf.java.sqlreflect.sql.item.Column;
import usf.java.sqlreflect.sql.item.Table;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private boolean columns;
	private TableTypes type;
	
	public TableScanner(ConnectionManager cm) {
		this(cm, TableTypes.TABLE);
	}
	
	public TableScanner(ConnectionManager cm, TableTypes type) {
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
				ColumnScanner ts = new ColumnScanner(getConnectionManager());
				while(rs.next()){
					Table t = mapper.map(rs, row+1);
					List<Column> cols = ts.set(t.getDatabaseName(), t.getName(), null).run();
					t.setColumns(cols);
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
