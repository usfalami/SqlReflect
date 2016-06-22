package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.reflect.scanner.list.ScannerListMapper;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.exception.AdapterException;

public class ProcedureScanner extends Reflector implements Scanner {
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm);
	}

	public <P extends Function, C extends Column> void run(ScannerAdapter<P> adapter, Mapper<C> columnMapper, String databasePattern, String proecedurePattern) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter, columnMapper, databasePattern, proecedurePattern);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}
	
	protected <P extends Function, C extends Column> void run(DatabaseMetaData dm, ScannerAdapter<P> adapter, Mapper<C> columnMapper, String databasePattern, String proecedurePattern) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getProcedures(null, databasePattern, proecedurePattern);
			if(columnMapper == null) {
				while(rs.next()){
					P p = adapter.getMapper().map(rs, row+1);
					adapter.adapte(p, row++);
				}
			}
			else{ // look for columns
				ColumnScanner cs = new ColumnScanner(cm);
				ScannerListMapper<C> sm = new ScannerListMapper<C>(columnMapper);
				while(rs.next()){
					P p = adapter.getMapper().map(rs, row+1);
					cs.run(sm, p.getDatabase(), p.getName(), null);
					p.setColumns(sm.getList());
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
