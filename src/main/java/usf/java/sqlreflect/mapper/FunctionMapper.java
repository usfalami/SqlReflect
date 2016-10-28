package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.item.Function;
import usf.java.sqlreflect.sql.type.FunctionTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class FunctionMapper extends AbstractItemMapper<Function> {

	@Override
	public Function map(ResultSet rs, int row) throws Exception {
		Function f = new Function();
		f.setDatabaseName(rs.getString(getServerConstants().FUNCTION_DATABASE));
		f.setName(rs.getString(SqlConstants.FUNCTION_NAME));
		f.setType(FunctionTypes.values()[rs.getInt(SqlConstants.FUNCTION_TYPE)].toString());
		return f;
	}

	@Override
	public void write(StreamWriter writer, Function function) throws Exception {
		writer.startObject("FUNCTION");
		writer.writeString(SqlConstants.DATABASE_NAME, function.getDatabaseName());
		writer.writeString(SqlConstants.FUNCTION_NAME, function.getName());
		writer.writeString(SqlConstants.FUNCTION_TYPE, function.getType());
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.DATABASE_NAME, SqlConstants.FUNCTION_NAME, SqlConstants.FUNCTION_TYPE};
	}

}
