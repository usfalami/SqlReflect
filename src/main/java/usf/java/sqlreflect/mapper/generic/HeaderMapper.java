package usf.java.sqlreflect.mapper.generic;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.mapper.GenericTypeTemplate;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.SimpleProperty;
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
	
	private static final GenericTypeTemplate<Header> complexObject;
	
	static {
		Class<String> stringClass = String.class;
		Class<Integer> integerClass = Integer.class;
		complexObject = new GenericTypeTemplate<Header>(Header.class);
		complexObject.getFields().add(new SimpleProperty<String>(SqlConstants.DATABASE_NAME, stringClass));
		complexObject.getFields().add(new SimpleProperty<String>(SqlConstants.TABLE_NAME, stringClass));
		complexObject.getFields().add(new SimpleProperty<String>(SqlConstants.COLUMN_NAME, stringClass));
		complexObject.getFields().add(new SimpleProperty<Integer>(SqlConstants.COLUMN_TYPE, integerClass));
		complexObject.getFields().add(new SimpleProperty<String>(SqlConstants.TYPE_NAME, stringClass));
		complexObject.getFields().add(new SimpleProperty<Integer>(SqlConstants.COLUMN_SIZE, integerClass));
		complexObject.getFields().add(new SimpleProperty<String>(SqlConstants.COLUMN_CLASS, stringClass));
	}
}