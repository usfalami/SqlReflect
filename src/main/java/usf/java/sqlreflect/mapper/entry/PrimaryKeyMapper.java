package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.PrimaryKey;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class PrimaryKeyMapper extends SimpleObjectMapper<PrimaryKey> {
	
	public PrimaryKeyMapper() {
		super(new GenericTypeTemplate<PrimaryKey>(PrimaryKey.class, 
			SqlConstants.TABLE_NAME,
			SqlConstants.COLUMN_NAME,
			SqlConstants.PK_NAME,
			SqlConstants.KEY_SEQ));
	}
	
	@Override
	public Template<PrimaryKey> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}

}
