package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.DatabaseMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Database;

public class DatabaseScanner extends AbstractFieldScanner<Database> {
	
	private String databasePattern;
	
	public DatabaseScanner(ConnectionManager cm) {
		super(cm, new DatabaseMapper());
	}
	public DatabaseScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new DatabaseMapper());
	}

	@Override
	protected ResultSet getResultSet(DatabaseMetaData dm) throws Exception {
		return Utils.isEmpty(databasePattern) ? dm.getSchemas() : dm.getSchemas(null, databasePattern);
	}
	
	public DatabaseScanner set(String databasePattern){
		this.databasePattern = databasePattern;
		return this;
	}
}
