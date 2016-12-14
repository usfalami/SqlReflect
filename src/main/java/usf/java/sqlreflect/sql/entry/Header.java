package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class Header extends Entry {
	
	public String getName() {
		return getString(SqlConstants.COLUMN_NAME);
	}
	public void setName(String name) {
		set(SqlConstants.COLUMN_NAME, name);
	}
	
	public int getType() {
		return getInteger(SqlConstants.COLUMN_TYPE);
	}
	public void setType(int dataType) {
		set(SqlConstants.COLUMN_TYPE, dataType);
	}
	
	public String getTypeName() {
		return getString(SqlConstants.TYPE_NAME);
	}
	public void setTypeName(String dataTypeName) {
		set(SqlConstants.TYPE_NAME, dataTypeName);
	}
	
	public int getSize() {
		return getInteger(SqlConstants.COLUMN_SIZE);
	}
	public void setSize(int size) {
		set(SqlConstants.COLUMN_SIZE, size);
	}
	
	public String getClassName() {
		return getString(SqlConstants.COLUMN_CLASS);
	}
	public void setClassName(String dataTypeName) {
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
	
}
