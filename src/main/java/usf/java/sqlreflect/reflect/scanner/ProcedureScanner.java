package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.adapter.ListAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.connection.manager.TransactionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.ProcedureMapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Argument;
import usf.java.sqlreflect.sql.item.Procedure;

public class ProcedureScanner extends AbstractFieldScanner<Procedure> {
	
	private boolean arguments;
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm);
	}
	public ProcedureScanner(TransactionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	public ProcedureScanner set(boolean columns) {
		this.arguments = columns;
		return this;
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Procedure> adapter, String arg1, String arg2, String arg3) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getProcedures(null, arg1, arg2);
			action.end();
			
			Mapper<Procedure> mapper = new ProcedureMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			if(arguments) { // look for columns
				ArgumentScanner cs = new ArgumentScanner(getConnectionManager()); //TODO  : something
				while(rs.next()){
					Procedure p = mapper.map(rs, row+1);
					List<Argument> args = cs.run(p.getDatabaseName(), p.getName(), null);	
					p.setArguments(args);
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
	
	public final List<Procedure> run(String databasePattern, String tablePattern) throws Exception {
		ListAdapter<Procedure> adapter = new ListAdapter<Procedure>();
		super.run(adapter, databasePattern, tablePattern,  null);
		return adapter.getList();
	}
	public void run(Adapter<Procedure> adapter, String databasePattern, String tablePattern) throws Exception {
		super.run(adapter, databasePattern, tablePattern, null);
	}
	
}
