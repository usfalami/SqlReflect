package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryPropertyMapper;
import usf.java.sqlreflect.mapper.FiltredMapper;
import usf.java.sqlreflect.mapper.filter.LabelIndexConverter;
import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.ImportedKey;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ImprotedKeyRule;

public class ImportedKeyMapper extends FiltredMapper<ImportedKey> {
	
	public ImportedKeyMapper() {
		super(ImportedKey.class, new EntryPropertyMapper<ImportedKey>(),
				SqlConstants.PKTABLE_NAME, SqlConstants.PKCOLUMN_NAME, SqlConstants.PK_NAME, 
				SqlConstants.KEY_SEQ,
				SqlConstants.FKTABLE_NAME, SqlConstants.FKCOLUMN_NAME, SqlConstants.FK_NAME);
	}
	
	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PK_TABLE_DATABASE, SqlConstants.PK_DATABASE_NAME);
		addFilter(type.FK_TABLE_DATABASE, SqlConstants.FK_DATABASE_NAME);
		addFilter(SqlConstants.UPDATE_RULE, new LabelIndexConverter<ImprotedKeyRule>(ImprotedKeyRule.class));
		addFilter(SqlConstants.DELETE_RULE, new LabelIndexConverter<ImprotedKeyRule>(ImprotedKeyRule.class));
		return super.prepare(rs, type);
	}

}
