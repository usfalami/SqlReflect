package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.reflect.scanner.SimpleFieldListAdapter;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.mapper.Mapper;

public class ProcedureScanner implements Scanner {
	
	public <T extends Function, C extends Column> void run(HasScanner<T> adapter, Mapper<C> columnMapper, String databasePattern, String proecedurePattern) throws SQLException {
		adapter.start();
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter, columnMapper, databasePattern, proecedurePattern);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(cnx);
		}
		adapter.end();
	}
	
	protected <T extends Function, C extends Column> void run(DatabaseMetaData dm, HasScanner<T> adapter, Mapper<C> columnMapper, String databasePattern, String proecedurePattern) throws SQLException {
		adapter.start();
		ResultSet rs = null;
		try {
			int row = 0;
			rs = dm.getProcedures(null, databasePattern, proecedurePattern);
			if(columnMapper == null) {
				while(rs.next()){
					T p = adapter.getMapper().map(rs, row+1);
					adapter.adapte(p, row++);
				}
			}
			else{ // look for columns
				SimpleFieldListAdapter<C> columnAdaper = new SimpleFieldListAdapter<C>(adapter.getConnectionManager(), columnMapper);
				ColumnScanner cs = new ColumnScanner();
				while(rs.next()){
					T p = adapter.getMapper().map(rs, row+1);
					cs.run(columnAdaper, p.getDatabase(), p.getName(), null);
					p.setColumns(columnAdaper.getList());
					adapter.adapte(p, row++);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(rs);
			adapter.end();
		}
	}
	
}
