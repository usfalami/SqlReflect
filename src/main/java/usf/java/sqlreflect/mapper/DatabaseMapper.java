package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public class DatabaseMapper extends AdvancedEntryMapper<Database> {

	public DatabaseMapper() {
		super(Database.class, SqlConstants.DATABASE_COLUMNS);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, Database database) throws Exception {
		writer.startObject("DATABASE");
		writer.writeString(SqlConstants.DATABASE_NAME, database.getName());
		writer.endObject();
	}
	
}
