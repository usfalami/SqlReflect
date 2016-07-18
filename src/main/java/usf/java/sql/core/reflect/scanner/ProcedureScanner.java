package usf.java.sql.core.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.adapter.ListAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.field.types.HasColumn;
import usf.java.sql.core.mapper.ProcedureMapper;

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
			ProcedureMapper mapper = new ProcedureMapper();
			adapter.headers(mapper.getColumnNames());
			if(columns) { // look for columns
				ColumnScanner cs = new ColumnScanner(cm, HasColumn.PROCEDURE);
				ListAdapter<Column> ca = new ListAdapter<Column>();
				
				while(rs.next()){
					Procedure p = mapper.map(rs, row+1);
					cs.set(p.getDatabase(), p.getName(), null).run(dm, ca);
					p.setColumns(ca.getList());
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
			cm.close(rs);
			adapter.end();
		}
	}
	
}
