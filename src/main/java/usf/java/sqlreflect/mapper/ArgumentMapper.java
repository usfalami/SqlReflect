package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.IntegerEnumConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ArgumentMapper extends AdvancedEntryMapper<Argument> {
	
	public ArgumentMapper() {
		super(Argument.class, 
				SqlConstants.PROCEDURE_NAME, SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_TYPE, 
				SqlConstants.DATA_TYPE, SqlConstants.TYPE_NAME, SqlConstants.LENGTH);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.COLUMN_TYPE, new IntegerEnumConverter<ParameterTypes>(ParameterTypes.class));
		super.prepare(rs, type);
	}

}
