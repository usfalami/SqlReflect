package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Table;
import usf.java.sql.core.field.types.TableType;
import usf.java.sql.core.mapper.TableMapper;
import usf.java.sql.core.reflect.Reflector;

public class TableScanner extends Reflector implements Scanner {
	
	public TableScanner(ConnectionManager cm) {
		super(cm);
	}

	public void run(ScannerAdapter<Table> adapter, String databasePattern, String proecedurePattern, boolean columnMapper) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.getConnection();
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
	
	protected void run(DatabaseMetaData dm, ScannerAdapter<Table> adapter, String databasePattern, String tablePattern, boolean columns) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getTables(null, databasePattern, tablePattern, new String[]{TableType.TABLE.toString()});
			TableMapper mapper = new TableMapper();
			adapter.headers(mapper.getColumnNames());
//			if(columns) { // look for columns
//				ParameterScanner ps = new ParameterScanner(cm);
//				ScannerListMapper<Parameter> sm = new ScannerListMapper<Parameter>();
//				
//				while(rs.next()){
//					Procedure p = mapper.map(rs, row+1);
//					ps.run(dm, sm, p.getDatabase(), p.getName(), null);
//					p.setColumns(sm.getList());
//					adapter.adapte(p, row++);
//				}
//			}
//			else
			{
				while(rs.next()){
					Table p = mapper.map(rs, row+1);
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
