package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ArgumentMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.item.Argument;

public class ArgumentScanner extends AbstractFieldScanner<Argument> {
	
	private String databasePattern, procedurePattern, argumentPattern;
	
	public ArgumentScanner(ConnectionManager cm) {
		super(cm, new ArgumentMapper(cm.getServer().getType()));
	}
	public ArgumentScanner(ConnectionManager cm, ActionTimer at) {
		super(cm, at, new ArgumentMapper(cm.getServer().getType()));
	}

	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getArguments(dm, databasePattern, procedurePattern, argumentPattern);
	}

	public ArgumentScanner set(String databasePattern, String procedurePattern, String argumentPattern) {
		this.databasePattern = databasePattern;
		this.procedurePattern = procedurePattern;
		this.argumentPattern = argumentPattern;
		return this;
	}

}
