package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class HeaderMapper implements Mapper<Header> {
	
	private DatabaseType type;

	@Override
	public Collection<Property> prepare(ResultSet rs, DatabaseType type) {
		this.type = type;
		return properties;
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

	private static final Collection<Property> properties = Arrays.asList(
			new Property(SqlConstants.DATABASE_NAME).setClassName(String.class.getName()),
			new Property(SqlConstants.TABLE_NAME)	.setClassName(String.class.getName()),
			new Property(SqlConstants.COLUMN_NAME)	.setClassName(String.class.getName()),
			new Property(SqlConstants.COLUMN_TYPE)	.setClassName(Integer.class.getName()),
			new Property(SqlConstants.TYPE_NAME)	.setClassName(String.class.getName()),
			new Property(SqlConstants.COLUMN_SIZE)	.setClassName(Integer.class.getName()),
			new Property(SqlConstants.COLUMN_CLASS)	.setClassName(String.class.getName())
		);
	
}