package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryPropertyMapper;
import usf.java.sqlreflect.mapper.FiltredMapper;
import usf.java.sqlreflect.mapper.filter.LabelIndexConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;

public class ArgumentMapper extends FiltredMapper<Argument> {
	
	public ArgumentMapper() {
		super(Argument.class, new EntryPropertyMapper<Argument>(),
				SqlConstants.PROCEDURE_NAME, SqlConstants.COLUMN_NAME, 
				SqlConstants.DATA_TYPE, SqlConstants.TYPE_NAME, SqlConstants.LENGTH);
	}
	
	@Override
	public List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.COLUMN_TYPE, new LabelIndexConverter<ParameterTypes>(ParameterTypes.class));
		return super.prepare(rs, type);
	}

}
