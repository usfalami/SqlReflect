package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.EntryTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ArgumentMapper extends SimpleObjectMapper<Argument> {
	
	public ArgumentMapper() {
		super(new EntryTemplate<Argument>(Argument.class, 
			SqlConstants.PROCEDURE_NAME,
			SqlConstants.COLUMN_NAME,
			SqlConstants.DATA_TYPE,
			SqlConstants.TYPE_NAME,
			SqlConstants.LENGTH));
	}
	
	@Override
	public Template<Argument> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.COLUMN_TYPE, 
				new LabelIndexConverter<ParameterTypes>(ParameterTypes.class)
			));
		return super.prepare(rs, type);
	}

}
