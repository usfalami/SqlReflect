package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ColumnMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Column;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	private String databasePattern, tablePattern, columnPattern;
	
	public ColumnScanner(ConnectionManager cm) {
		super(cm);
	}
	public ColumnScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	public ColumnScanner set(String databasePattern, String tablePattern, String columnPattern) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columnPattern = columnPattern;
		return this;
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Column> adapter) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getColumns(null, databasePattern, tablePattern, columnPattern);
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
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
