package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.ArgumentMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.sql.item.Argument;

public class ArgumentScanner extends AbstractFieldScanner<Argument> {
	
	private String databasePattern, procedurePattern, argumentPattern;
	
	public ArgumentScanner(ConnectionManager cm) {
		super(cm);
	}
	public ArgumentScanner(ConnectionManager cm, TimePerform tp) {
		super(cm, tp);
	}
	
	public ArgumentScanner set(String databasePattern, String procedurePattern, String argumentPattern) {
		this.databasePattern = databasePattern;
		this.procedurePattern = procedurePattern;
		this.argumentPattern = argumentPattern;
		return this;
	}

	@Override
	protected void runScan(DatabaseMetaData dm, Adapter<Argument> adapter) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = getTimePerform().startAction(Constants.ACTION_EXECUTION);
			rs = dm.getProcedureColumns(null, databasePattern, procedurePattern, argumentPattern);
			action.end();
			
			Mapper<Argument> mapper = new ArgumentMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = getTimePerform().startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Argument argument = mapper.map(rs, row+1);
				adapter.adapte(argument, row++);
			}
			action.end();
			getTimePerform().setRowCount(row);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
