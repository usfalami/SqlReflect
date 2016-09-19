package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.sql.ParameterTypes;
import usf.java.sqlreflect.stream.StreamWriter;

public class ColumnProcedureMapper implements Mapper<Argument> {

	@Override
	public Argument map(ResultSet rs, int row) throws Exception {
		Argument c = new Argument();
		c.setDatabaseName(rs.getString("PROCEDURE_SCHEM"));
		c.setSourceName(rs.getString("PROCEDURE_NAME"));
		c.setName(rs.getString("COLUMN_NAME"));
		c.setType(ParameterTypes.values()[rs.getInt("COLUMN_TYPE")].toString());
		c.setSqlType(rs.getInt("DATA_TYPE"));
		c.setValueType(rs.getString("TYPE_NAME"));
		c.setSize(rs.getInt("LENGTH"));
		return c;
	}

	@Override
	public void write(StreamWriter writer, Argument parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString("PROCEDURE_SCHEM", parameter.getDatabaseName());
		writer.writeString("PROCEDURE_NAME", parameter.getSourceName());
		writer.writeString("COLUMN_NAME", parameter.getName());
		writer.writeString("COLUMN_TYPE", parameter.getType());
		writer.writeInt("DATA_TYPE", parameter.getSqlType());
		writer.writeString("TYPE_NAME", parameter.getValueType());
		writer.writeLong("LENGTH", parameter.getSize());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"PROCEDURE_SCHEM", "PROCEDURE_NAME", "COLUMN_NAME", "COLUMN_TYPE", "DATA_TYPE", "TYPE_NAME", "LENGTH"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
