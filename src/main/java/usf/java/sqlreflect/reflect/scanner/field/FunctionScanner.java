package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.entry.FunctionMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Function;

public class FunctionScanner extends AbstractFieldScanner<Function> {

	private String databasePattern, functionPattern;
	
	public FunctionScanner(ConnectionManager cm) {
		super(cm, new FunctionMapper());
	}
	public FunctionScanner(TransactionManager cm, ActionTimer at) {
		super(cm, at, new FunctionMapper());
	}
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return dm.getFunctions(null, databasePattern, functionPattern);
	}
	
	public FunctionScanner set(String databasePattern, String functionPattern, boolean arguments) {
		this.databasePattern = databasePattern;
		this.functionPattern = functionPattern;
		return this;
	}
	public FunctionScanner set(String databasePattern, String functionPattern) {
		return set(databasePattern, functionPattern, false);
	}

}
