package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.List;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.field.Procedure;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.ProcedureMapper;
import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;

public class ProcedureScanner extends AbstractFieldScanner<Procedure> {
	
	private String databasePattern, proecedurePattern;
	private boolean columns;
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm);
	}
	
	public ProcedureScanner set(String databasePattern, String proecedurePattern, boolean columns) {
		this.databasePattern = databasePattern;
		this.proecedurePattern = proecedurePattern;
		this.columns = columns;
		return this;
	}

	@Override
	protected void run(DatabaseMetaData dm, Adapter<Procedure> adapter, TimePerform tp) throws Exception {
		ResultSet rs = null;
		try {

			ActionPerform action = tp.startAction(EXECUTION);
			rs = dm.getProcedures(null, databasePattern, proecedurePattern);
			action.end();
			
			Mapper<Procedure> mapper = new ProcedureMapper();
			adapter.prepare(mapper);
			int row = 0;

			action = tp.startAction(ADAPT);
			if(columns) { // look for columns
				ColumnScanner cs = new ColumnScanner(getConnectionManager(), HasColumn.PROCEDURE);
				while(rs.next()){
					Procedure p = mapper.map(rs, row+1);
					List<Column> columns = cs.set(p.getDatabase(), p.getName(), null).run();
					p.setColumns(columns);
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
			tp.setRowCount(row);
			
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
		}
	}
	
}
