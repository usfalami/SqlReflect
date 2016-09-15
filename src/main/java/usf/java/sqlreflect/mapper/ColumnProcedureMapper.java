package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.sql.ParameterTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class ColumnProcedureMapper implements Mapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws Exception {
		return new Column(
			rs.getString("COLUMN_NAME").toString(),
			rs.getString("TYPE_NAME").toString(),
			rs.getInt("LENGTH"),
			ParameterTypes.values()[rs.getInt("COLUMN_TYPE")].toString()
		);
	}

	@Override
	public void write(StreamWriter writer, Column parameter) throws Exception {
		writer.startObject("COLUMN");
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
