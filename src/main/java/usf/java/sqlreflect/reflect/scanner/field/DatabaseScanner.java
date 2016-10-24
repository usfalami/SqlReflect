package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.DatabaseMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.item.Database;

public class DatabaseScanner extends AbstractFieldScanner<Database> {
	
	private String databasePattern;
	
	public DatabaseScanner(ConnectionManager cm) {
		super(cm, new DatabaseMapper(cm.getServer().getType()));
	}
	public DatabaseScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new DatabaseMapper(cm.getServer().getType()));
	}

	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getDatabase(dm, databasePattern);
	}
	
	public DatabaseScanner set(String databasePattern){
		this.databasePattern = databasePattern;
		return this;
	}
}
