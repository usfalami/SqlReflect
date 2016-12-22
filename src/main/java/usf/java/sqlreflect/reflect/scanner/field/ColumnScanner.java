package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.entry.ColumnMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Column;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	private String databasePattern, tablePattern, columnPattern;
	
	public ColumnScanner(ConnectionManager cm) {
		super(cm, new ColumnMapper());
	}
	public ColumnScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new ColumnMapper());
	}
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getColumns(dm, databasePattern, tablePattern, columnPattern);
	}

	public ColumnScanner set(String databasePattern, String tablePattern, String columnPattern) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.columnPattern = columnPattern;
		return this;
	}
	
}
