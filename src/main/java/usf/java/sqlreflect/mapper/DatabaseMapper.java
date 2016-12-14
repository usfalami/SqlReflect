package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class DatabaseMapper extends AdvancedEntryMapper<Database> {

	public DatabaseMapper() {
		super(Database.class);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}
	
}
