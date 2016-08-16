package usf.java.sqlreflect.core.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.core.adapter.ScannerAdapter;
import usf.java.sqlreflect.core.connection.manager.ConnectionManager;
import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.field.Column;
import usf.java.sqlreflect.core.mapper.Mapper;

public class ColumnScanner extends AbstractFieldScanner<Column> {
	
	private String databasePattern, proecedurePattern, columnPattern;
	private HasColumn field;
	
	public ColumnScanner(ConnectionManager cm, HasColumn field) {
		super(cm);
		this.field = field;
	}
	
	public ColumnScanner set(String databasePattern, String proecedurePattern, String columnPattern) {
		this.databasePattern = databasePattern;
		this.proecedurePattern = proecedurePattern;
		this.columnPattern = columnPattern;
		return this;
	}

	@Override
	protected void run(DatabaseMetaData dm, ScannerAdapter<Column> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = field.getColumns(dm, databasePattern, proecedurePattern, columnPattern);
			Mapper<Column> mapper = field.getMapper();
			int row = 0;
			adapter.prepare(mapper);
			while(rs.next()){
				Column column = mapper.map(rs, row+1);
				adapter.adapte(column, row++);
			}
		} catch (SQLException e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
			adapter.end();
		}
	}

}
