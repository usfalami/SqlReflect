package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryHandler;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class DatabaseMapper extends GenericMapper<Database> {

	public DatabaseMapper() {
		super(Database.class, new EntryHandler());
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(new Metadata(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME));
		return super.prepare(rs, type);
	}
	
}
