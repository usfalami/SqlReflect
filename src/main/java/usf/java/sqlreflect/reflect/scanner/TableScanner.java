package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.field.Table;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.TableMapper;

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
	protected void run(DatabaseMetaData dm, ScannerAdapter<Table> adapter) throws Exception {
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getTables(null, databasePattern, tablePattern, new String[]{TableType.TABLE.toString()});
			Mapper<Table> mapper = new TableMapper();
			adapter.prepare(mapper);
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
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}
	
}
