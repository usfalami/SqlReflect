package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.ProcedureMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Procedure;

public class ProcedureScanner extends AbstractFieldScanner<Procedure> {

	private String databasePattern, procedurePattern;
	private boolean arguments;
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm, new ProcedureMapper());
	}
	public ProcedureScanner(TransactionManager cm, ActionTimer at) {
		super(cm, at, new ProcedureMapper());
	}

	@Override
	protected ResultSet runScan(DatabaseMetaData dm) throws Exception {
		return dm.getProcedures(null, databasePattern, procedurePattern);
	}
	
	public ProcedureScanner set(String databasePattern, String procedurePattern, boolean arguments) {
		this.databasePattern = databasePattern;
		this.procedurePattern = procedurePattern;
		this.arguments = arguments;
		return this;
	}
	public ProcedureScanner set(String databasePattern, String procedurePattern) {
		return set(databasePattern, procedurePattern, false);
	}
}
