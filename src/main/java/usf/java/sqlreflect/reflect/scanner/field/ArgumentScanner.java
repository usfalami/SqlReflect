package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ArgumentMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Argument;

public class ArgumentScanner extends AbstractFieldScanner<Argument> {
	
	private String databasePattern, procedurePattern, argumentPattern;
	
	public ArgumentScanner(ConnectionManager cm) {
		super(cm, new ArgumentMapper());
	}
	public ArgumentScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new ArgumentMapper());
	}

	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return dm.getProcedureColumns(null, databasePattern, procedurePattern, argumentPattern);
	}

	public ArgumentScanner set(String databasePattern, String procedurePattern, String argumentPattern) {
		this.databasePattern = databasePattern;
		this.procedurePattern = procedurePattern;
		this.argumentPattern = argumentPattern;
		return this;
	}

}