package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.reflect.scanner.ScannerListMapper;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Parameter;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.exception.AdapterException;

public class ProcedureScanner extends Reflector implements Scanner {
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm);
	}

	public void run(ScannerAdapter<Procedure> adapter, String databasePattern, String proecedurePattern, boolean columnMapper) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter, databasePattern, proecedurePattern, columnMapper);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}
	
	protected void run(DatabaseMetaData dm, ScannerAdapter<Procedure> adapter, String databasePattern, String proecedurePattern, boolean columnMapper) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getProcedures(null, databasePattern, proecedurePattern);
			ProcedureMapper mapper = new ProcedureMapper();
			adapter.headers(mapper.getColumnNames());
			if(columnMapper) { // look for columns
				ParameterScanner ps = new ParameterScanner(cm);
				ScannerListMapper<Parameter> sm = new ScannerListMapper<Parameter>();
				
				while(rs.next()){
					Procedure p = mapper.map(rs, row+1);
					ps.run(dm, sm, p.getDatabase(), p.getName(), null);
					p.setColumns(sm.getList());
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
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(rs);
			adapter.end();
		}
	}
	
}
