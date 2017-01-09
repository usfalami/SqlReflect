package usf.java.sqlreflect.mapper.generic;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ArgumentMapper extends GenericTypeMapper<Argument> {
	
	public ArgumentMapper() {
		super(Argument.class, 
			SqlConstants.PROCEDURE_NAME, 
			SqlConstants.COLUMN_NAME,
			SqlConstants.DATA_TYPE,
			SqlConstants.TYPE_NAME,
			SqlConstants.LENGTH);
	}
	
	@Override
	public Template<Argument> prepare(ResultSet rs, DatabaseType type) throws Exception {
		Converter<String> converter = new LabelIndexConverter<ParameterTypes>(ParameterTypes.class);
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.PROCEDURE_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.COLUMN_TYPE, converter));
		return super.prepare(rs, type);
	}

}
