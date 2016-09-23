package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.item.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class HeaderMapper implements Mapper<Header> {

	@Override
	public Header map(ResultSet rs, int row) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		Header h = new Header();
		h.setName(md.getColumnName(row));
		h.setType(md.getColumnType(row));
		h.setTypeName(md.getColumnTypeName(row));
		h.setSize(md.getColumnDisplaySize(row));
		h.setClassName(md.getColumnClassName(row));
		return h;
	}

	@Override
	public void write(StreamWriter writer, Header column) throws Exception {
		writer.startObject("HEADER");
		writer.writeString(SqlConstants.COLUMN_NAME, column.getName());
		writer.writeInt(SqlConstants.COLUMN_TYPE, column.getType());
		writer.writeString(SqlConstants.TYPE_NAME, column.getTypeName());
		writer.writeInt(SqlConstants.COLUMN_SIZE, column.getSize());
		writer.writeString(SqlConstants.COLUMN_CLASS, column.getClassName());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{SqlConstants.COLUMN_NAME, SqlConstants.COLUMN_TYPE, SqlConstants.TYPE_NAME, SqlConstants.COLUMN_SIZE, SqlConstants.COLUMN_CLASS};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
	}

}