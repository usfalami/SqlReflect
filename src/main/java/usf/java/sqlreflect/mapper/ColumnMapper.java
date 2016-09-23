package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.item.Column;
import usf.java.sqlreflect.stream.StreamWriter;

public class ColumnMapper implements Mapper<Column> {

	@Override
	public Column map(ResultSet rs, int row) throws Exception {
		Column c = new Column();
		c.setDatabaseName(rs.getString(SqlConstants.TABLE_SCHEM));
		c.setTableName(rs.getString(SqlConstants.TABLE_NAME));
		c.setName(rs.getString(SqlConstants.COLUMN_NAME));
		c.setDataType(rs.getInt(SqlConstants.DATA_TYPE));
		c.setDataTypeName(rs.getString(SqlConstants.TYPE_NAME));
		c.setSize(rs.getInt(SqlConstants.COLUMN_SIZE));
		return c;
	}

	@Override
	public void write(StreamWriter writer, Column parameter) throws Exception {
		writer.startObject("COLUMN");
		writer.writeString(SqlConstants.TABLE_SCHEM, parameter.getDatabaseName());
		writer.writeString(SqlConstants.TABLE_NAME, parameter.getTableName());
		writer.writeString(SqlConstants.COLUMN_NAME, parameter.getName());
		writer.writeInt(SqlConstants.DATA_TYPE, parameter.getDataType());
		writer.writeString(SqlConstants.TYPE_NAME, parameter.getDataTypeName());
		writer.writeInt(SqlConstants.COLUMN_SIZE, parameter.getSize());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.TABLE_SCHEM, SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, SqlConstants.DATA_TYPE, SqlConstants.TYPE_NAME, SqlConstants.COLUMN_SIZE};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		
	}

}
