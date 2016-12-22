package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class HeaderMapper implements Mapper<Header> {
	
	private DatabaseType type;

	@Override
	public List<Header> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		this.type = type;
		String stringClassName = String.class.getName();
		return Arrays.asList(
				new Header(SqlConstants.DATABASE_NAME, 	stringClassName),
				new Header(SqlConstants.TABLE_NAME, 	stringClassName),
				new Header(SqlConstants.COLUMN_NAME, 	stringClassName),
				new Header(SqlConstants.COLUMN_TYPE, 	stringClassName),
				new Header(SqlConstants.TYPE_NAME, 		stringClassName),
				new Header(SqlConstants.COLUMN_SIZE,	Integer.class.getName()),
				new Header(SqlConstants.COLUMN_CLASS, 	stringClassName)
			);
	}

	@Override
	public Header map(ResultSet rs, int row) throws SQLException {
		Header header = new Header();
		ResultSetMetaData md = rs.getMetaData();
		header.setDatabaseName(DatabaseType.CATALOG.equals(type) ? 
				md.getCatalogName(row) : md.getSchemaName(row));
		header.setTableName(md.getTableName(row));
		header.setColumnName(md.getColumnName(row));
		header.setColumnType(md.getColumnType(row));
		header.setColumnTypeName(md.getColumnTypeName(row));
		header.setColumnSize(md.getColumnDisplaySize(row));
		header.setColumnClassName(md.getColumnClassName(row));
		return header;
	}
	
	
	@Override
	public Class<Header> getMappedClass() {
		return Header.class;
	}

}