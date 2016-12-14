package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.IntegerEnumConverter;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends AdvancedEntryMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class, 
				SqlConstants.FUNCTION_NAME, SqlConstants.FUNCTION_TYPE);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.FUNCTION_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.FUNCTION_TYPE, new IntegerEnumConverter<FunctionTypes>(FunctionTypes.class));
		super.prepare(rs, type);
	}

}
