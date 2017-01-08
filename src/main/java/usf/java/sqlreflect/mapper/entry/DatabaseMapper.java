package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.Database;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class DatabaseMapper extends SimpleObjectMapper<Database> {

	public DatabaseMapper() {
		super(new GenericTypeTemplate<Database>(Database.class));
	}
	
	@Override
	public Template<Database> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}
	
}
