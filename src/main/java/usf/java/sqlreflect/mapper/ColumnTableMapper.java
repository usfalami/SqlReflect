package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.item.Argument;
import usf.java.sqlreflect.stream.StreamWriter;

public class ColumnTableMapper implements Mapper<Argument> {

	@Override
	public Argument map(ResultSet rs, int row) throws Exception {
		Argument c = new Argument();
		c.setDatabaseName(rs.getString("TABLE_SCHEM"));
		c.setSourceName(rs.getString("TABLE_NAME"));
		c.setName(rs.getString("COLUMN_NAME"));
		c.setSqlType(rs.getInt("DATA_TYPE"));
		c.setValueType(rs.getString("TYPE_NAME"));
		c.setSize(rs.getInt("COLUMN_SIZE"));
		return c;
	}

	@Override
	public void write(StreamWriter writer, Argument parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString("TABLE_SCHEM", parameter.getDatabaseName());
		writer.writeString("TABLE_NAME", parameter.getSourceName());
		writer.writeString("COLUMN_NAME", parameter.getName());
		writer.writeInt("DATA_TYPE", parameter.getSqlType());
		writer.writeString("TYPE_NAME", parameter.getValueType());
		writer.writeInt("COLUMN_SIZE", parameter.getSize());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"TABLE_SCHEM", "TABLE_NAME", "COLUMN_NAME", "DATA_TYPE", "TYPE_NAME", "COLUMN_SIZE"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
