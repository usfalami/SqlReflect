package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends SimpleObjectMapper<Function> {
	
	public FunctionMapper() {
		super(new GenericTypeTemplate<Function>(Function.class, 
				SqlConstants.FUNCTION_NAME));
	}
	
	@Override
	public Template<Function> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, type.FUNCTION_DATABASE));
		appendProperty(new SimpleProperty<String>(SqlConstants.FUNCTION_TYPE, 
				new LabelIndexConverter<FunctionTypes>(FunctionTypes.class)
			));
		return super.prepare(rs, type);
	}

}
