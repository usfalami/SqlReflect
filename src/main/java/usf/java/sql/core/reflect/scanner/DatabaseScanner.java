package usf.java.sql.core.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.mapper.DatabaseMapper;
import usf.java.sql.core.reflect.ReflectorUtils;

public class DatabaseScanner extends AbstractFieldScanner<Database> {
	
	private String databasePattern;
	
	public DatabaseScanner(ConnectionManager cm) {
		super(cm);
	}
	
	public DatabaseScanner set(String databasePattern) {
		this.databasePattern = databasePattern;
		return this;
	}

	protected void run(DatabaseMetaData dm, ScannerAdapter<Database> adapter) throws SQLException, AdapterException {
		adapter.start();
		ResultSet rs = null;
		try {
			rs = ReflectorUtils.isEmpty(databasePattern) ? dm.getSchemas() : dm.getSchemas(null, databasePattern);
			DatabaseMapper mapper = new DatabaseMapper();
			int row = 0;
			adapter.headers(mapper.getColumnNames());
			while(rs.next()){
				Database database = mapper.map(rs, row+1);
				adapter.adapte(database, row++);
			}
		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			cm.close(rs);
			adapter.end();
		}
	}

}
