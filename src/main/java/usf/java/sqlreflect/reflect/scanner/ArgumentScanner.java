package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.mapper.ArgumentMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public class ArgumentScanner extends AbstractFieldScanner<Argument> {
	
	private String databasePattern, procedurePattern, columnPattern;
	
	public ArgumentScanner(ConnectionManager cm) {
		super(cm);
	}
	
	public ArgumentScanner set(String databasePattern, String procedurePattern, String columnPattern) {
		this.databasePattern = databasePattern;
		this.procedurePattern = procedurePattern;
		this.columnPattern = columnPattern;
		return this;
	}

	@Override
	protected void run(DatabaseMetaData dm, Adapter<Argument> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = tp.startAction(Constants.ACTION_EXECUTION);
			rs = dm.getProcedureColumns(null, databasePattern, procedurePattern, columnPattern);
			action.end();
			
			Mapper<Argument> mapper = new ArgumentMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = tp.startAction(Constants.ACTION_ADAPT);
			while(rs.next()){
				Argument argument = mapper.map(rs, row+1);
				adapter.adapte(argument, row++);
			}
			action.end();
			tp.setRowCount(row);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}

}
