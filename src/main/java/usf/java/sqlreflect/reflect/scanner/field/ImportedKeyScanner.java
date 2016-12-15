package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ImportedKeyMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.ImportedKey;

public class ImportedKeyScanner extends AbstractFieldScanner<ImportedKey> {

	private String databasePattern, tablePattern;

	public ImportedKeyScanner(ConnectionManager cm) {
		super(cm, new ImportedKeyMapper());
	}
	
	public ImportedKeyScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new ImportedKeyMapper());
	}

	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getImportedKeys(dm, databasePattern, tablePattern);
	}
	
	public ImportedKeyScanner set(String databasePattern, String tablePattern) {
		this.databasePattern = databasePattern;
		this.tablePattern = tablePattern;
		return this;
	}


}
