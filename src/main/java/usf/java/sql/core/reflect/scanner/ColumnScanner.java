package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;

public class ColumnScanner implements Scanner {
	
	public <C extends Column> void run(HasScanner<C> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException {
		Connection cnx = null;
		try {
			cnx = adapter.getConnectionManager().newConnection();
			DatabaseMetaData dm = cnx.getMetaData();
			this.run(dm, adapter, databasePattern, proecedurePattern, columnPattern);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			adapter.getConnectionManager().close(cnx);
		}
	}
	
	public <C extends Column> void run(DatabaseMetaData dm, HasScanner<C> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException {
		ResultSet rs = null;
		try {
			rs = dm.getProcedureColumns(null, databasePattern, proecedurePattern, null);
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
			adapter.getConnectionManager().close(rs);
		}
	}

}
