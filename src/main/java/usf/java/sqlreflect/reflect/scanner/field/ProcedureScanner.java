package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.entry.ProcedureMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.entry.Procedure;

public class ProcedureScanner extends AbstractFieldScanner<Procedure> {

	private String databasePattern, procedurePattern;
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm, new ProcedureMapper());
	}
	public ProcedureScanner(TransactionManager cm, ActionTimer at) {
		super(cm, at, new ProcedureMapper());
	}
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getProcedures(dm, databasePattern, procedurePattern);
	}
	
	public ProcedureScanner set(String databasePattern, String procedurePattern) {
		this.databasePattern = databasePattern;
		this.procedurePattern = procedurePattern;
		return this;
	}
}
