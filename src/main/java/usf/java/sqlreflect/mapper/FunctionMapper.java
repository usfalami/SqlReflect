package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.FunctionTypes;

public class FunctionMapper extends FiltredEntryMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class, SqlConstants.FUNCTION_NAME);
	}
	
	@Override
	public Collection<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.FUNCTION_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.FUNCTION_TYPE, new LabelIndexConverter<FunctionTypes>(FunctionTypes.class));
		return super.prepare(rs, type);
	}

}
