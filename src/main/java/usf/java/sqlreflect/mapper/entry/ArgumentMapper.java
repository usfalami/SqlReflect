package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ArgumentMapper extends SimpleObjectMapper<Argument> {
	
	public ArgumentMapper() {
		super(Argument.class);
		appendProperty(new EntryProperty<String>(SqlConstants.PROCEDURE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.COLUMN_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.DATA_TYPE));
		appendProperty(new EntryProperty<String>(SqlConstants.TYPE_NAME));
		appendProperty(new EntryProperty<String>(SqlConstants.LENGTH));
	}
	
	@Override
	public Template<Argument> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new EntryProperty<String>(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		appendProperty(new EntryProperty<String>(SqlConstants.COLUMN_TYPE, 
				new LabelIndexConverter<ParameterTypes>(ParameterTypes.class)
			));
		return super.prepare(rs, type);
	}

}
