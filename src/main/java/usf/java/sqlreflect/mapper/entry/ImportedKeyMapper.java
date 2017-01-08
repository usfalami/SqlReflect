package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.ImportedKey;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ImprotedKeyRule;

public class ImportedKeyMapper extends SimpleObjectMapper<ImportedKey> {
	
	public ImportedKeyMapper() {
		super(new GenericTypeTemplate<ImportedKey>(ImportedKey.class,	
			SqlConstants.PKTABLE_NAME,
			SqlConstants.PKCOLUMN_NAME,
			SqlConstants.PK_NAME,
			SqlConstants.KEY_SEQ,
			SqlConstants.FKTABLE_NAME,
			SqlConstants.FKCOLUMN_NAME,
			SqlConstants.FK_NAME));
	}
	
	@Override
	public Template<ImportedKey> prepare(ResultSet rs, DatabaseType type) throws Exception {
		Converter<String> converter = new LabelIndexConverter<ImprotedKeyRule>(ImprotedKeyRule.class);
		appendProperty(new SimpleProperty<String>(SqlConstants.PK_DATABASE_NAME, type.PK_TABLE_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.FK_DATABASE_NAME, type.FK_TABLE_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.UPDATE_RULE, converter));
		appendProperty(new SimpleProperty<String>(SqlConstants.DELETE_RULE, converter));
		return super.prepare(rs, type);
	}

}
