package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;
import usf.java.sqlreflect.writer.TypeWriter;

public class HeaderMapper implements Mapper<Header> {
	
	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {}

	@Override
	public Header map(ResultSet rs, int row) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		Header h = new Header();
		h.setTableName(md.getTableName(row));
		h.setName(md.getColumnName(row));
		h.setType(md.getColumnType(row));
		h.setTypeName(md.getColumnTypeName(row));
		h.setSize(md.getColumnDisplaySize(row));
		h.setClassName(md.getColumnClassName(row));
		return h;
	}

	@Override
	public Map<String, TypeWriter> getTypes() throws SQLException {
		Map<String, TypeWriter> types = new HashMap<String, TypeWriter>();
		types.put(SqlConstants.TABLE_NAME, TypeWriter.STRING);
		types.put(SqlConstants.COLUMN_NAME, TypeWriter.STRING);
		types.put(SqlConstants.COLUMN_TYPE, TypeWriter.INTEGER);
		types.put(SqlConstants.TYPE_NAME, TypeWriter.STRING);
		types.put(SqlConstants.COLUMN_SIZE, TypeWriter.INTEGER);
		types.put(SqlConstants.COLUMN_CLASS, TypeWriter.STRING);
		return types;
	}

}