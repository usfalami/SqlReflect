package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.ImportedKey;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ImprotedKeyRule;

public class ImportedKeyMapper extends SimpleObjectMapper<ImportedKey> {
	
	public ImportedKeyMapper() {
		super(ImportedKey.class);
		appendProperty(new EntryProperty<String>(SqlConstants.PKTABLE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.PKCOLUMN_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.PK_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.KEY_SEQ));
		appendProperty(new EntryProperty<String>(SqlConstants.FKTABLE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.FKCOLUMN_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.FK_NAME));
	}
	
	@Override
	public ComplexObject<ImportedKey> prepare(ResultSet rs, DatabaseType type) throws Exception {
		Converter<String> converter = new LabelIndexConverter<ImprotedKeyRule>(ImprotedKeyRule.class);
		appendProperty(new EntryProperty<String>(SqlConstants.PK_DATABASE_NAME, type.PK_TABLE_DATABASE));
		appendProperty(new EntryProperty<String>(SqlConstants.FK_DATABASE_NAME, type.FK_TABLE_DATABASE));
		appendProperty(new EntryProperty<String>(SqlConstants.UPDATE_RULE, converter));
		appendProperty(new EntryProperty<String>(SqlConstants.DELETE_RULE, converter));
		return super.prepare(rs, type);
	}

}
