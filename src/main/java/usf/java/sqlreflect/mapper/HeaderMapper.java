package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

import usf.java.sqlreflect.field.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class HeaderMapper implements Mapper<Header> {

	@Override
	public Header map(ResultSet rs, int row) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		Header h = new Header();
		h.setName(md.getColumnName(row));
		h.setValueType(md.getColumnTypeName(row));
		h.setSize(md.getColumnDisplaySize(row));
		h.setClassName(md.getColumnClassName(row));
		h.setSqlType(md.getColumnType(row));
		return h;
	}

	@Override
	public void write(StreamWriter writer, Header column) throws Exception {
		writer.startObject("HEADER");
		writer.writeString("HEADER_NAME", column.getName());
		writer.writeString("HEADER_TYPE", column.getValueType());
		writer.writeString("TYPE_NAME", column.getValueType());
		writer.writeInt("LENGTH", column.getSize());
		writer.writeString("CLASS", column.getClassName());
		writer.endObject();
	}
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"HEADER_NAME", "HEADER_TYPE", "TYPE_NAME", "LENGTH", "CLASS"};
	}
	
	@Override
	public void setColumnNames(String... columnNames) {
		// TODO Auto-generated method stub
	}

}