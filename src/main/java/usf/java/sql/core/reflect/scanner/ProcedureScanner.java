package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.adapter.reflect.scanner.SimpleFieldListAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.reflect.Reflector;

public class ProcedureScanner extends Reflector implements Scanner {
	
	public ProcedureScanner(ConnectionManager cm) {
		super(cm);
	}

	public <T extends Function, C extends Column> void run(HasScanner<T> adapter, Mapper<C> columnMapper, String databasePattern, String proecedurePattern) throws SQLException {
		adapter.start();
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
				SimpleFieldListAdapter<C> columnAdaper = new SimpleFieldListAdapter<C>(columnMapper);
				ColumnScanner cs = new ColumnScanner(cm);
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
			cm.close(rs);
			adapter.end();
		}
	}
	
}
