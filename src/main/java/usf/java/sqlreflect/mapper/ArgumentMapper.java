package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.filter.IntegerEnumConverter;
import usf.java.sqlreflect.sql.entry.Argument;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.sql.type.ParameterTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class ArgumentMapper extends AdvancedEntryMapper<Argument> {
	
	public ArgumentMapper() {
		super(Argument.class, SqlConstants.ARGUMENT_COLUMNS);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addFilter(type.PROCEDURE_DATABASE, SqlConstants.DATABASE_NAME);
		addFilter(SqlConstants.COLUMN_TYPE, new IntegerEnumConverter<ParameterTypes>(ParameterTypes.class));
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, Argument parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString(SqlConstants.DATABASE_NAME, parameter.getDatabaseName());
		writer.writeString(SqlConstants.PROCEDURE_NAME, parameter.getCallableName());
		writer.writeString(SqlConstants.COLUMN_NAME, parameter.getName());
		writer.writeString(SqlConstants.COLUMN_TYPE, parameter.getType());
		writer.writeInt(SqlConstants.DATA_TYPE, parameter.getDataType());
		writer.writeString(SqlConstants.TYPE_NAME, parameter.getDataTypeName());
		writer.writeLong(SqlConstants.LENGTH, parameter.getSize());
		writer.endObject();
	}
	
}
