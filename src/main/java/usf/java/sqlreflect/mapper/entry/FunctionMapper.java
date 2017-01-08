package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends SimpleObjectMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class);
		appendProperty(new EntryProperty<String>(SqlConstants.FUNCTION_NAME));
	}
	
	@Override
	public ComplexObject<Function> prepare(ResultSet rs, DatabaseType type) throws Exception {
		appendProperty(new EntryProperty<String>(SqlConstants.DATABASE_NAME, type.FUNCTION_DATABASE));
		appendProperty(new EntryProperty<String>(SqlConstants.FUNCTION_TYPE, 
				new LabelIndexConverter<FunctionTypes>(FunctionTypes.class)
			));
		return super.prepare(rs, type);
	}

}
