package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class ColumnMapper extends SimpleObjectMapper<Column> {
	
	public ColumnMapper() {
		super(Column.class);
		appendProperty(new EntryProperty<String>(SqlConstants.TABLE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.COLUMN_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.DATA_TYPE));
		appendProperty(new EntryProperty<String>(SqlConstants.TYPE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.COLUMN_SIZE));
	}
	
	@Override
	public Template<Column> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new EntryProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}

}
