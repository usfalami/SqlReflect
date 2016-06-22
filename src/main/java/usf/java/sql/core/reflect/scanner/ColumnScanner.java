package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.exception.AdapterException;

public class ColumnScanner extends Reflector implements Scanner {
	
	public ColumnScanner(ConnectionManager cm) {
		super(cm);
	}

	public <C extends Column> void run(HasScanner<C> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			run(dm, adapter, databasePattern, proecedurePattern, columnPattern);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			cm.close(cnx);
		}
	}
	
	protected <C extends Column> void run(DatabaseMetaData dm, HasScanner<C> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = dm.getProcedureColumns(null, databasePattern, proecedurePattern, columnPattern);
			int row = 0;
			while(rs.next()){
				C column = adapter.getMapper().map(rs, row+1);
				adapter.adapte(column, row++);
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
