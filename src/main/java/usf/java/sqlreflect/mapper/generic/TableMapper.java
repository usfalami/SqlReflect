package usf.java.sqlreflect.mapper.generic;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class TableMapper extends GenericTypeMapper<Table> {
	
	public TableMapper() {
		super(Table.class, 
				SqlConstants.TABLE_NAME,
				SqlConstants.TABLE_TYPE);
	}
	
	@Override
	public Template<Table> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.TABLE_DATABASE));
		return super.prepare(rs, type);
	}

}
