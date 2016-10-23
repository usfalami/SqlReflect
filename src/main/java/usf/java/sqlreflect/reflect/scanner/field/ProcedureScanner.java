package usf.java.sqlreflect.reflect.scanner.field;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.ProcedureMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.sql.item.Argument;
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
	protected void runAdapt(ResultSet rs, Adapter<Procedure> adapter, ActionTimer at) throws Exception {
		if(!arguments) super.runAdapt(rs, adapter, at);
		else {
			int row = 0;
			ArgumentScanner as = new ArgumentScanner(getConnectionManager()); 
			ListAdapter<Argument> aa = new ListAdapter<Argument>();
			while(rs.next()){
				Procedure p = getMapper().map(rs, row+1);
				as.set(p.getDatabaseName(), p.getName(), null).run(aa, at.createAction());	
				p.setArguments(aa.getList());
				adapter.adapte(p, row++);
			}
		}
	}
	
	@Override
	protected ResultSet runExecution(DatabaseMetaData dm) throws Exception {
		return getConnectionManager().getServer().getProcedures(dm, databasePattern, procedurePattern);
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
