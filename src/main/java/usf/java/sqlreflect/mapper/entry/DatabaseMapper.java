package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class DatabaseMapper extends SimpleObjectMapper<Database> {

	public DatabaseMapper() {
		super(Database.class, new EntryBuilder());
	}
	
	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) throws Exception {
		addPropertyFilter(new Property(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}
	
}
