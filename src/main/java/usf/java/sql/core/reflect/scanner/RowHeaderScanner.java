package usf.java.sql.core.reflect.scanner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Header;
import usf.java.sql.core.mapper.HeaderMapper;
import usf.java.sql.core.mapper.Mapper;

public class RowHeaderScanner extends AbstractDataScanner<Header> {
	
	public RowHeaderScanner(ConnectionManager cm) {
		super(cm);
	}

	protected void run(Statement stmt, ScannerAdapter<Header> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = cm.executeQuery(stmt, callable.getSQL(), parameters);
			Mapper<Header> mapper = new HeaderMapper();
			ResultSetMetaData rm = rs.getMetaData();
			adapter.headers(mapper.getColumnNames());
			for(int i=1; i<=rm.getColumnCount(); i++) {
				Header col = mapper.map(rs, i);
				adapter.adapte(col, i);
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
