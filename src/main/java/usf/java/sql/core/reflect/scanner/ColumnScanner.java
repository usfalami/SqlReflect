package usf.java.sql.core.reflect.scanner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.types.HasColumn;
import usf.java.sql.core.mapper.ParameterMapper;
import usf.java.sql.core.reflect.Reflector;

public class ColumnScanner extends Reflector implements Scanner {
	
	private HasColumn field;
	
	public ColumnScanner(ConnectionManager cm, HasColumn field) {
		super(cm);
		this.field = field;
	}

	public void run(ScannerAdapter<Column> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException, AdapterException {
		Connection cnx = null;
		try {
			cnx = cm.getConnection();
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
	
	protected void run(DatabaseMetaData dm, ScannerAdapter<Column> adapter, String databasePattern, String proecedurePattern, String columnPattern) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = field.getColumns(null, databasePattern, proecedurePattern, columnPattern);
			ParameterMapper mapper = new ParameterMapper();
			int row = 0;
			adapter.headers(mapper.getColumnNames());
			while(rs.next()){
				Column column = mapper.map(rs, row+1);
				adapter.adapte(column, row++);
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
