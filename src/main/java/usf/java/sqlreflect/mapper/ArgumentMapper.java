package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.ParameterTypes;
import usf.java.sqlreflect.sql.item.Argument;
import usf.java.sqlreflect.stream.StreamWriter;

public class ArgumentMapper implements Mapper<Argument> {

	@Override
	public Argument map(ResultSet rs, int row) throws Exception {
		Argument c = new Argument();
		c.setDatabaseName(rs.getString(SqlConstants.PROCEDURE_SCHEM));
		c.setCallableName(rs.getString(SqlConstants.PROCEDURE_NAME));
		c.setName(rs.getString(SqlConstants.COLUMN_NAME));
		c.setType(ParameterTypes.values()[rs.getInt(SqlConstants.COLUMN_TYPE)].toString());
		c.setDataType(rs.getInt(SqlConstants.DATA_TYPE));
		c.setDataTypeName(rs.getString(SqlConstants.TYPE_NAME));
		c.setSize(rs.getInt(SqlConstants.LENGTH));
		return c;
	}

	@Override
	public void write(StreamWriter writer, Argument parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString(SqlConstants.PROCEDURE_SCHEM, parameter.getDatabaseName());
		writer.writeString(SqlConstants.PROCEDURE_NAME, parameter.getCallableName());
		writer.writeString(SqlConstants.COLUMN_NAME, parameter.getName());
		writer.writeString(SqlConstants.COLUMN_TYPE, parameter.getType());
		writer.writeInt(SqlConstants.DATA_TYPE, parameter.getDataType());
		writer.writeString(SqlConstants.TYPE_NAME, parameter.getDataTypeName());
		writer.writeLong(SqlConstants.LENGTH, parameter.getSize());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.PROCEDURE_SCHEM, SqlConstants.PROCEDURE_NAME, SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_TYPE, SqlConstants.DATA_TYPE, SqlConstants.TYPE_NAME, SqlConstants.LENGTH};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
