package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class HeaderMapper implements Mapper<Header> {
	
	private DatabaseType type;

	public HeaderMapper() {
		
	}

	@Override
	public Collection<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		this.type = type;
		return Arrays.asList(
				new Header(SqlConstants.DATABASE_NAME, String.class.getName()),
				new Header(SqlConstants.TABLE_NAME, String.class.getName()),
				new Header(SqlConstants.COLUMN_NAME, String.class.getName()),
				new Header(SqlConstants.COLUMN_TYPE, String.class.getName()),
				new Header(SqlConstants.TYPE_NAME, String.class.getName()),
				new Header(SqlConstants.COLUMN_SIZE, Integer.class.getName()),
				new Header(SqlConstants.COLUMN_CLASS, String.class.getName())
			);
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

}