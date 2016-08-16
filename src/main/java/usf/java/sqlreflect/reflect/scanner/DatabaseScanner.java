package usf.java.sqlreflect.reflect.scanner;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Database;
import usf.java.sqlreflect.mapper.DatabaseMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ReflectorUtils;

public class DatabaseScanner extends AbstractFieldScanner<Database> {
	
	private String databasePattern;
	
	public DatabaseScanner(ConnectionManager cm) {
		super(cm);
	}
	
	public DatabaseScanner set(String databasePattern) {
		this.databasePattern = databasePattern;
		return this;
	}

	protected void run(DatabaseMetaData dm, ScannerAdapter<Database> adapter) throws Exception {
		ResultSet rs = null;
		try {
			adapter.start();
			rs = ReflectorUtils.isEmpty(databasePattern) ? dm.getSchemas() : dm.getSchemas(null, databasePattern);
			Mapper<Database> mapper = new DatabaseMapper();
			int row = 0;
			adapter.prepare(mapper);
			while(rs.next()){
				Database database = mapper.map(rs, row+1);
				adapter.adapte(database, row++);
			}
		}
		catch(Exception e) {
			throw e;
		}
		finally {
			getConnectionManager().close(rs);
			adapter.end();
		}
	}

}
