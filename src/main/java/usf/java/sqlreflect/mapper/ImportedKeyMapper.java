package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.IntegerEnumConverter;
import usf.java.sqlreflect.sql.entry.ImportedKey;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ImprotedKeyRule;

public class ImportedKeyMapper extends AdvancedEntryMapper<ImportedKey> {
	
	public ImportedKeyMapper() {
		super(ImportedKey.class,
				SqlConstants.PKTABLE_NAME, SqlConstants.PKCOLUMN_NAME, SqlConstants.PK_NAME, 
				SqlConstants.KEY_SEQ,
				SqlConstants.FKTABLE_NAME, SqlConstants.FKCOLUMN_NAME, SqlConstants.FK_NAME
				);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PK_TABLE_DATABASE, SqlConstants.PK_DATABASE_NAME);
		addFilter(type.FK_TABLE_DATABASE, SqlConstants.FK_DATABASE_NAME);
		addFilter(SqlConstants.UPDATE_RULE, new IntegerEnumConverter<ImprotedKeyRule>(ImprotedKeyRule.class));
		addFilter(SqlConstants.DELETE_RULE, new IntegerEnumConverter<ImprotedKeyRule>(ImprotedKeyRule.class));
		super.prepare(rs, type);
	}

}
