package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Table;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class TableMapper extends AdvancedEntryMapper<Table> {
	
	public TableMapper() {
		super(Table.class, SqlConstants.TABLE_NAME, SqlConstants.TABLE_TYPE);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}

}
