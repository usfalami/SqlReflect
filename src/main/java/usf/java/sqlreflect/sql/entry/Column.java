package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class Column extends Entry {
	
	public String getDatabaseName() {
		return getString(SqlConstants.DATABASE_NAME);
	}
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.DATABASE_NAME, databaseName);
	}
	
	public String getTableName() {
		return getString(SqlConstants.TABLE_NAME);
	}
	public void setTableName(String tableName) {
		set(SqlConstants.TABLE_NAME, tableName);
	}
	
	public String getName() {
		return getString(SqlConstants.COLUMN_NAME);
	}
	public void setName(String name) {
		set(SqlConstants.COLUMN_NAME, name);
	}
	
	public int getDataType() {
		return getInteger(SqlConstants.DATA_TYPE);
	}
	public void setDataType(int dataType) {
		set(SqlConstants.DATA_TYPE, dataType);
	}
	
	public String getDataTypeName() {
		return getString(SqlConstants.TYPE_NAME);
	}
	public void setDataTypeName(String dataTypeName) {
		set(SqlConstants.TYPE_NAME, dataTypeName);
	}
	
	public int getSize() {
		return getInteger(SqlConstants.COLUMN_SIZE);
	}
	public void setSize(int size) {
		set(SqlConstants.COLUMN_SIZE, size);
	}
	
}
