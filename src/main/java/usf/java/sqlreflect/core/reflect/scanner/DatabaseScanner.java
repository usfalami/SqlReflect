package usf.java.sqlreflect.core.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.core.adapter.ScannerAdapter;
import usf.java.sqlreflect.core.connection.manager.ConnectionManager;
import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.field.Database;
import usf.java.sqlreflect.core.mapper.DatabaseMapper;
import usf.java.sqlreflect.core.mapper.Mapper;
import usf.java.sqlreflect.core.reflect.ReflectorUtils;

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
			Mapper<Database> mapper = new DatabaseMapper();
			int row = 0;
			adapter.prepare(mapper);
			while(rs.next()){
				Database database = mapper.map(rs, row+1);
				adapter.adapte(database, row++);
			}
		}
		catch(SQLException e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
			adapter.end();
		}
	}

}
