package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.PrimaryKeyMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.item.PrimaryKey;

public class PrimaryKeyScanner extends AbstractFieldScanner<PrimaryKey>{

	private String databasePattern, tablePattern;
	
	public PrimaryKeyScanner(ConnectionManager cm) {
		super(cm, new PrimaryKeyMapper());
	}
	public PrimaryKeyScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new PrimaryKeyMapper());
	}
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getPrimaryKes(dm, databasePattern, tablePattern);
	}
	
	public PrimaryKeyScanner set(String databasePattern, String tablePattern) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		return this;
	}

}
