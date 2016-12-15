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

public class HeaderMapper extends EntryMapper<Header> {
	
	private DatabaseType type;

	public HeaderMapper() {
		super(Header.class, new String[]{
				SqlConstants.DATABASE_NAME, SqlConstants.TABLE_NAME, SqlConstants.COLUMN_NAME, 
				SqlConstants.COLUMN_TYPE, SqlConstants.TYPE_NAME, SqlConstants.COLUMN_SIZE, 
				SqlConstants.COLUMN_CLASS
		});
	}

	@Override
	public void prepare(ResultSet rs, DatabaseType type) throws SQLException {
		this.type = type;
	}

	@Override
	public Header map(ResultSet rs, int row) throws Exception {
		ResultSetMetaData md = rs.getMetaData();
		Header h = new Header();
		h.setDatabaseName(DatabaseType.CATALOG.equals(type) ?
				md.getCatalogName(row) : md.getSchemaName(row));
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