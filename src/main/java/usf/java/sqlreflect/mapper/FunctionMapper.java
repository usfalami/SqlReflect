package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.item.Function;
import usf.java.sqlreflect.sql.type.FunctionTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class FunctionMapper implements Mapper<Function> {

	@Override
	public Function map(ResultSet rs, int row) throws Exception {
		Function f = new Function();
		f.setDatabaseName(rs.getString(SqlConstants.FUNCTION_SCHEM));
		f.setName(rs.getString(SqlConstants.FUNCTION_NAME));
		f.setType(FunctionTypes.values()[rs.getInt(SqlConstants.FUNCTION_TYPE)].toString());
		return f;
	}

	@Override
	public void write(StreamWriter writer, Function function) throws Exception {
		writer.startObject("FUNCTION");
		writer.writeString(SqlConstants.FUNCTION_SCHEM, function.getDatabaseName());
		writer.writeString(SqlConstants.FUNCTION_NAME, function.getName());
		writer.writeString(SqlConstants.FUNCTION_TYPE, function.getType());
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.FUNCTION_SCHEM, SqlConstants.FUNCTION_NAME, SqlConstants.FUNCTION_TYPE};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
	}

}
