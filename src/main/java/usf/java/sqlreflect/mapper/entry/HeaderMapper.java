package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.EntryTemplate;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class HeaderMapper implements Mapper<Header> {
	
	private DatabaseType type;
	
	@Override
	public Template<Header> prepare(ResultSet rs, DatabaseType type) {
		this.type = type;
		return complexObject;
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
	
	private static final Template<Header> complexObject = 
		new EntryTemplate<Header>(Header.class,
			SqlConstants.DATABASE_NAME,
			SqlConstants.TABLE_NAME,
			SqlConstants.COLUMN_NAME,
			SqlConstants.COLUMN_TYPE,
			SqlConstants.TYPE_NAME,
			SqlConstants.COLUMN_SIZE,
			SqlConstants.COLUMN_CLASS
		);
	
}