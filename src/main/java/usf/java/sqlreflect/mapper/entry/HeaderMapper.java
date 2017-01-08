package usf.java.sqlreflect.mapper.entry;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.mapper.EntryProperty;
import usf.java.sqlreflect.mapper.Field;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.sql.type.DatabaseType;

public class HeaderMapper implements Mapper<Header> {
	
	private DatabaseType type;
	
	@Override
	public ComplexObject<Header> prepare(ResultSet rs, DatabaseType type) {
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
	static{
		List<Field<?>> list = new ArrayList<Field<?>>();
		complexObject = new ComplexObject<Header>(Header.class, list);
		list.add(new EntryProperty<String>(SqlConstants.DATABASE_NAME));
		list.add(new EntryProperty<String>(SqlConstants.TABLE_NAME));
		list.add(new EntryProperty<String>(SqlConstants.COLUMN_NAME));
		list.add(new EntryProperty<Integer>(SqlConstants.COLUMN_TYPE));
		list.add(new EntryProperty<String>(SqlConstants.TYPE_NAME));
		list.add(new EntryProperty<Integer>(SqlConstants.COLUMN_SIZE));
		list.add(new EntryProperty<String>(SqlConstants.COLUMN_CLASS));	
	}
	
	private static final ComplexObject<Header> complexObject;
	
}