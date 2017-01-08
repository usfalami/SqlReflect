package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class TableMapper extends SimpleObjectMapper<Table> {
	
	public TableMapper() {
		super(Table.class);
		appendProperty(new EntryProperty<String>(SqlConstants.TABLE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.TABLE_TYPE));
	}
	
	@Override
	public ComplexObject<Table> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new EntryProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}

}
