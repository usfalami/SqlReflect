package usf.java.sqlreflect.reflect.scanner;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Header;
import usf.java.sqlreflect.mapper.HeaderMapper;
import usf.java.sqlreflect.mapper.Mapper;

public class HeaderScanner extends AbstractDataScanner<Header> {
	
	public HeaderScanner(ConnectionManager cm) {
		super(cm);
	}

	@Override
	protected void run(Statement stmt, ScannerAdapter<Header> adapter) throws Exception {
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
		} catch (Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
			adapter.end();
		}
	}

}
