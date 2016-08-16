package usf.java.sql.core.reflect.scanner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import usf.java.sql.core.adapter.ScannerAdapter;
import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Header;
import usf.java.sql.core.mapper.HeaderMapper;
import usf.java.sql.core.mapper.Mapper;

public class HeaderScanner extends AbstractDataScanner<Header> {
	
	public HeaderScanner(ConnectionManager cm) {
		super(cm);
	}

	@Override
	protected void run(Statement stmt, ScannerAdapter<Header> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = getConnectionManager().executeQuery(stmt, getCallable().getSQL());
			Mapper<Header> mapper = new HeaderMapper();
			ResultSetMetaData rm = rs.getMetaData();
			adapter.prepare(mapper);
			for(int i=1; i<=rm.getColumnCount(); i++) {
				Header col = mapper.map(rs, i);
				adapter.adapte(col, i);
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
