package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Parameter;
import usf.java.sql.core.stream.StreamWriter;

public class ParameterMapper implements Mapper<Parameter> {

	@Override
	public Parameter map(ResultSet rs, int row) throws SQLException {
		return new Parameter(
			rs.getString("COLUMN_NAME").toString(),
			rs.getString("TYPE_NAME").toString(),
			rs.getInt("LENGTH"),
			rs.getInt("COLUMN_TYPE")
		);
	}


	@Override
	public void write(StreamWriter writer, Parameter parameter) throws Exception {
		writer.startObject("COLUMN", new String[]{"COLUMN_NAME", "TYPE_NAME", "LENGTH", "COLUMN_TYPE"});
		writer.writeString("COLUMN_NAME", parameter.getName());
		writer.writeString("TYPE_NAME", parameter.getValueType());
		writer.writeInt("LENGTH", parameter.getSize());
		writer.writeString("COLUMN_TYPE", parameter.getRole().toString());
		writer.endObject();
	}

}
