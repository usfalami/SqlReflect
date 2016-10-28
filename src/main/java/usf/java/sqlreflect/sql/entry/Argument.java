package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class Argument extends Entry {
	
	public String getDatabaseName() {
		return getString(SqlConstants.DATABASE_NAME);
	}
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.DATABASE_NAME, databaseName);
	}
	
	public String getCallableName() {
		return getString(SqlConstants.PROCEDURE_NAME);
	}
	public void setCallableName(String tableName) {
		set(SqlConstants.PROCEDURE_NAME, tableName);
	}
	
	public String getName() {
		return getString(SqlConstants.COLUMN_NAME);
	}
	public void setName(String name) {
		set(SqlConstants.COLUMN_NAME, name);
	}
	
	public String getType() {
		return getString(SqlConstants.COLUMN_TYPE);
	}
	public void setType(String name) {
		set(SqlConstants.COLUMN_TYPE, name);
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
		return getInteger(SqlConstants.LENGTH);
	}
	public void setSize(int size) {
		set(SqlConstants.LENGTH, size);
	}
	
}
