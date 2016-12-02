package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Function;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.stream.StreamWriter;

public class FunctionMapper extends AdvancedEntryMapper<Function> {
	
	public FunctionMapper() {
		super(Function.class, SqlConstants.FUNCTION_COLUMNS);
	}
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		addMapperFilter(SqlConstants.DATABASE_NAME, type.FUNCTION_DATABASE);
		super.prepare(rs, type);
	}

	@Override
	public void write(StreamWriter writer, Function function) throws Exception {
		writer.startObject("FUNCTION");
		writer.writeString(SqlConstants.DATABASE_NAME, function.getDatabaseName());
		writer.writeString(SqlConstants.FUNCTION_NAME, function.getName());
		writer.writeString(SqlConstants.FUNCTION_TYPE, function.getType());
		writer.endObject();
	}
}
