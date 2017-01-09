package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.generic.TableMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.TableTypes;

public class TableScanner extends AbstractFieldScanner<Table> {
	
	private String databasePattern, tablePattern;
	private String[] types;
	
	public TableScanner(ConnectionManager cm) {
		super(cm, new TableMapper());
	}
	public TableScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new TableMapper());
	}

	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getTables(dm, databasePattern, tablePattern, types);
	}
	
	public TableScanner set(String databasePattern, String tablePattern, TableTypes... types) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		this.types = Utils.isEmptyArray(types) ? new String[]{TableTypes.TABLE.toString()} : Utils.toString(types);
		return this;
	}

	public TableScanner set(String databasePattern, String tablePattern) {
		return set(databasePattern, tablePattern, TableTypes.TABLE);
	}
}
