package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Parameter;
import usf.java.sql.core.reflect.Reflector;
import usf.java.sql.core.reflect.exception.AdapterException;

public class ParameterScanner extends Reflector implements Scanner {
	
	public ParameterScanner(ConnectionManager cm) {
		super(cm);
	}

	public void run(ScannerAdapter<Parameter> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException, AdapterException {
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
	
	protected void run(DatabaseMetaData dm, ScannerAdapter<Parameter> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = dm.getProcedureColumns(null, databasePattern, proecedurePattern, columnPattern);
			int row = 0;
			while(rs.next()){
				Parameter column = adapter.getMapper().map(rs, row+1);
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
