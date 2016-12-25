package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class HeaderMapper implements Mapper<Header> {
	
	private DatabaseType type;

	@Override
	public Collection<Metadata> prepare(ResultSet rs, DatabaseType type) throws SQLException {
		this.type = type;
		String strClassName = String.class.getName(), intClassName = Integer.class.getName();
		return Arrays.asList(
				new Metadata(SqlConstants.DATABASE_NAME).setColumnClassName(strClassName),
				new Metadata(SqlConstants.TABLE_NAME)	.setColumnClassName(strClassName),
				new Metadata(SqlConstants.COLUMN_NAME)	.setColumnClassName(strClassName),
				new Metadata(SqlConstants.COLUMN_TYPE)	.setColumnClassName(intClassName),
				new Metadata(SqlConstants.TYPE_NAME)	.setColumnClassName(strClassName),
				new Metadata(SqlConstants.COLUMN_SIZE)	.setColumnClassName(intClassName),
				new Metadata(SqlConstants.COLUMN_CLASS)	.setColumnClassName(strClassName)
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