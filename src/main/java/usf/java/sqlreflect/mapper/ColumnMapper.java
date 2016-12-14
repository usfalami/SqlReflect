package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Column;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class ColumnMapper extends AdvancedEntryMapper<Column> {
	
	public ColumnMapper() {
		super(Column.class, 
				SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, SqlConstants.DATA_TYPE,
				SqlConstants.TYPE_NAME, SqlConstants.COLUMN_SIZE);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}

}
