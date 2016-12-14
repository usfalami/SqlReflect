package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.PrimaryKey;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class PrimaryKeyMapper extends AdvancedEntryMapper<PrimaryKey> {
	
	public PrimaryKeyMapper() {
		super(PrimaryKey.class, 
				SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, 
				SqlConstants.PK_NAME, SqlConstants.KEY_SEQ);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.TABLE_DATABASE, SqlConstants.DATABASE_NAME);
		super.prepare(rs, type);
	}

}
