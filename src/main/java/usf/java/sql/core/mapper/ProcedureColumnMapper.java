package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.types.ParameterRoles;
import usf.java.sql.core.stream.StreamWriter;

public class ProcedureColumnMapper implements Mapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws SQLException {
		return new Column(
			rs.getString("COLUMN_NAME").toString(),
			rs.getString("TYPE_NAME").toString(),
			rs.getInt("LENGTH"),
			ParameterRoles.values()[rs.getInt("COLUMN_TYPE")].toString()
		);
	}

	@Override
	public void write(StreamWriter writer, Column parameter) throws Exception {
		writer.startObject("COLUMN", getColumnNames());
		writer.writeString("COLUMN_NAME", parameter.getName());
		writer.writeString("TYPE_NAME", parameter.getValueType());
		writer.writeLong("LENGTH", parameter.getSize());
		writer.writeString("COLUMN_TYPE", parameter.getType().toString());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"COLUMN_NAME", "TYPE_NAME", "LENGTH", "COLUMN_TYPE"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
