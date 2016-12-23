package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class Header extends Entry {

	public Header() {
		// TODO Auto-generated constructor stub
	}
	
	public Header(String name, String className) {
		setPropertyName(name);
		setColumnClassName(name);
	}
	
	public String getColumnName() {
		return getString(SqlConstants.COLUMN_NAME);
	}
	public void setColumnName(String name) {
		set(SqlConstants.COLUMN_NAME, name);
	}
	
	public int getColumnType() {
		return getInteger(SqlConstants.COLUMN_TYPE);
	}
	public void setColumnType(int dataType) {
		set(SqlConstants.COLUMN_TYPE, dataType);
	}
	
	public String getColumnTypeName() {
		return getString(SqlConstants.TYPE_NAME);
	}
	public void setColumnTypeName(String dataTypeName) {
		set(SqlConstants.TYPE_NAME, dataTypeName);
	}
	
	public int getColumnSize() {
		return getInteger(SqlConstants.COLUMN_SIZE);
	}
	public void setColumnSize(int size) {
		set(SqlConstants.COLUMN_SIZE, size);
	}
	
	public String getColumnClassName() {
		return getString(SqlConstants.COLUMN_CLASS);
	}
	public void setColumnClassName(String dataTypeName) {
		set(SqlConstants.COLUMN_CLASS, dataTypeName);
	}
	
	public String getTableName() {
		return getString(SqlConstants.TABLE_NAME);
	}
	public void setTableName(String tableName) {
		set(SqlConstants.TABLE_NAME, tableName);
	}
	
	public String getDatabaseName() {
		return getString(SqlConstants.TABLE_NAME);
	}
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.DATABASE_NAME, databaseName);
	}
	
	public String getPropertyName() {
		return getString(SqlConstants.PROPERTY_NAME);
	}
	public void setPropertyName(String tableName) {
		set(SqlConstants.PROPERTY_NAME, tableName);
	}
	
}
