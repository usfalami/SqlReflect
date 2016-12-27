package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.DefaultMapper;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.mapper.PropertyConverter;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.mapper.converter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends DefaultMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class, new EntryBuilder(),
				SqlConstants.FUNCTION_NAME);
	}
	
	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) throws Exception {
		addPropertyFilter(new Property(SqlConstants.DATABASE_NAME, type.FUNCTION_DATABASE));
		addPropertyFilter(new PropertyConverter(SqlConstants.FUNCTION_TYPE, 
				new LabelIndexConverter<FunctionTypes>(FunctionTypes.class)
			));
		return super.prepare(rs, type);
	}

}
