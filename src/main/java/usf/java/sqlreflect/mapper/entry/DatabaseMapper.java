package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.DefaultMapper;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class DatabaseMapper extends DefaultMapper<Database> {

	public DatabaseMapper() {
		super(Database.class, new EntryBuilder());
	}
	
	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) throws Exception {
		addPropertyFilter(new Property(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME));
		return super.prepare(rs, type);
	}
	
}
