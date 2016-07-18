package usf.java.sql.core.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.adapter.ListAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Table;
import usf.java.sql.core.field.types.HasColumn;
import usf.java.sql.core.field.types.TableType;
import usf.java.sql.core.mapper.TableMapper;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private boolean columns;
	
	public TableScanner(ConnectionManager cm) {
		super(cm);
	}
	
	public TableScanner set(String databasePattern, String tablePattern, boolean columns) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columns = columns;
		return this;
	}

	@Override
	protected void run(DatabaseMetaData dm, ScannerAdapter<Table> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getTables(null, databasePattern, tablePattern, new String[]{TableType.TABLE.toString()});
			TableMapper mapper = new TableMapper();
			adapter.headers(mapper.getColumnNames());
			if(columns) { // look for columns
				ColumnScanner ts = new ColumnScanner(cm, HasColumn.TABLE);
				ListAdapter<Column> ta = new ListAdapter<Column>();
				
				while(rs.next()){
					Table t = mapper.map(rs, row+1);
					ts.set(t.getDatabase(), t.getName(), null).run(dm, ta);
					t.setColumns(ta.getList());
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
			
		} catch (SQLException e) {
			throw e;
		}
		finally {
			cm.close(rs);
			adapter.end();
		}
	}
	
}
