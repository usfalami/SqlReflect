package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.ProcedureMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Argument;
import usf.java.sqlreflect.sql.item.Procedure;

public class ProcedureScanner extends AbstractFieldScanner<Procedure> {

	private String databasePattern, procedurePattern;
	private boolean arguments;
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm);
	}
	public ProcedureScanner(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Procedure> adapter) throws Exception {
		ResultSet rs = null;
		try {

			ActionTimer action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getProcedures(null, databasePattern, procedurePattern);
			action.end();
			
			Mapper<Procedure> mapper = new ProcedureMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			if(arguments) { // look for arguments
				ArgumentScanner as = new ArgumentScanner(getConnectionManager()); 
				ListAdapter<Argument> aa = new ListAdapter<Argument>();
				while(rs.next()){
					Procedure p = mapper.map(rs, row+1);
					as.set(p.getDatabaseName(), p.getName(), null).runScan(dm, aa);	
					p.setArguments(aa.getList());
					adapter.adapte(p, row++);
				}
			}
			else{
				while(rs.next()){
					Procedure p = mapper.map(rs, row+1);
					adapter.adapte(p, row++);
				}
			}
			action.end();
			getTimePerform().setRowCount(row);
			
		}finally {
			getConnectionManager().close(rs);
		}
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
