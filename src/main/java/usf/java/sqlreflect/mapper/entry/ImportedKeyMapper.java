package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.mapper.PropertyConverter;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.ImportedKey;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ImprotedKeyRule;

public class ImportedKeyMapper extends SimpleObjectMapper<ImportedKey> {
	
	public ImportedKeyMapper() {
		super(ImportedKey.class, new EntryBuilder(),
				SqlConstants.PKTABLE_NAME, SqlConstants.PKCOLUMN_NAME, SqlConstants.PK_NAME, 
				SqlConstants.KEY_SEQ,
				SqlConstants.FKTABLE_NAME, SqlConstants.FKCOLUMN_NAME, SqlConstants.FK_NAME);
	}
	
	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) throws Exception {
		Converter<?> converter = new LabelIndexConverter<ImprotedKeyRule>(ImprotedKeyRule.class);
		addPropertyFilter(new Property(SqlConstants.PK_DATABASE_NAME, type.PK_TABLE_DATABASE));
		addPropertyFilter(new Property(SqlConstants.FK_DATABASE_NAME, type.FK_TABLE_DATABASE));
		addPropertyFilter(new PropertyConverter(SqlConstants.UPDATE_RULE, converter));
		addPropertyFilter(new PropertyConverter(SqlConstants.DELETE_RULE, converter));
		return super.prepare(rs, type);
	}

}
