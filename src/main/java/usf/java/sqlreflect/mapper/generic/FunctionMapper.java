package usf.java.sqlreflect.mapper.generic;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends GenericTypeMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class,
				SqlConstants.FUNCTION_NAME);
	}
	
	@Override
	public Template<Function> prepare(ResultSet rs, DatabaseType type) throws Exception {
		Converter<String> converter = new LabelIndexConverter<FunctionTypes>(FunctionTypes.class);
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.FUNCTION_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.FUNCTION_TYPE, converter));
		return super.prepare(rs, type);
	}

}
