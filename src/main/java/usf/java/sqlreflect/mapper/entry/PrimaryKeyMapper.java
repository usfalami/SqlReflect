package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.PrimaryKey;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class PrimaryKeyMapper extends SimpleObjectMapper<PrimaryKey> {
	
	public PrimaryKeyMapper() {
		super(PrimaryKey.class);
		appendProperty(new EntryProperty<String>(SqlConstants.TABLE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.COLUMN_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.PK_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.KEY_SEQ));
	}
	
	@Override
	public Template<PrimaryKey> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new EntryProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}

}
