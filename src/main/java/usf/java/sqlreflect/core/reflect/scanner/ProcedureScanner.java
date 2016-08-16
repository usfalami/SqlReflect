package usf.java.sqlreflect.core.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.core.adapter.ScannerAdapter;
import usf.java.sqlreflect.core.connection.manager.ConnectionManager;
import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.field.Column;
import usf.java.sqlreflect.core.field.Procedure;
import usf.java.sqlreflect.core.mapper.Mapper;
import usf.java.sqlreflect.core.mapper.ProcedureMapper;

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
	protected void run(DatabaseMetaData dm, ScannerAdapter<Procedure> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getProcedures(null, databasePattern, proecedurePattern);
			Mapper<Procedure> mapper = new ProcedureMapper();
			adapter.prepare(mapper);
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
			
		} catch (SQLException e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
			adapter.end();
		}
	}
	
}
