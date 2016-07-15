package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.sql.core.field.Header;
import usf.java.sql.core.stream.StreamWriter;

public class ColumnMapper implements Mapper<Header> {

	@Override
	public Header map(ResultSet rs, int row) throws SQLException {
		ResultSetMetaData md = rs.getMetaData();
		return new Header(
				md.getColumnName(row),
				md.getColumnTypeName(row),
				md.getColumnDisplaySize(row),
				md.getColumnClassName(row));
	}

	@Override
	public void write(StreamWriter writer, Header column) throws Exception {
		writer.startObject("COLUMN", getColumnNames());
		writer.writeString("COLUMN_NAME", column.getName());
		writer.writeString("TYPE_NAME", column.getType());
		writer.writeInt("LENGTH", column.getSize());
		writer.writeString("CLASS", column.getClazz());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"COLUMN_NAME", "TYPE_NAME", "LENGTH", "CLASS"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
	}

}
