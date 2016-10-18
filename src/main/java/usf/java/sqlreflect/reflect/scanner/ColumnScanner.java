package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ColumnMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Column;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	private String databasePattern, tablePattern, columnPattern;
	
	public ColumnScanner(ConnectionManager cm) {
		super(cm);
	}
	public ColumnScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at);
	}
	
	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Column> adapter, ActionTimer at) throws Exception {
		ResultSet rs = null;
		try {

			ActionTimer action = at.startAction(Constants.ACTION_EXECUTION);
			rs = dm.getColumns(null, databasePattern, tablePattern, columnPattern);
			action.end();
			
			Mapper<Column> mapper = new ColumnMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = at.startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Column column = mapper.map(rs, row+1);
				adapter.adapte(column, row++);
			}
			action.end();
			
		}finally {
			getConnectionManager().close(rs);
		}
	}

	public ColumnScanner set(String databasePattern, String tablePattern, String columnPattern) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columnPattern = columnPattern;
		return this;
	}
	
}
