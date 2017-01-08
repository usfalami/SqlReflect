package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class ColumnMapper extends SimpleObjectMapper<Column> {
	
	public ColumnMapper() {
		super(new GenericTypeTemplate<Column>(Column.class, 
			SqlConstants.TABLE_NAME,
			SqlConstants.COLUMN_NAME,
			SqlConstants.DATA_TYPE,
			SqlConstants.TYPE_NAME,
			SqlConstants.COLUMN_SIZE));
	}
	
	@Override
	public Template<Column> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}

}
