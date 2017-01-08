package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class PrimaryKey extends GenericType  {
	
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
	public String getColumnName() {
		return getString(SqlConstants.COLUMN_NAME);
	}
	public void setColumnName(String name) {
		set(SqlConstants.COLUMN_NAME, name);
	}
	public String getName() {
		return getString(SqlConstants.PK_NAME);
	}
	public void setName(String name) {
		set(SqlConstants.PK_NAME, name);
	}
	public int getKeySequence() {
		return getInteger(SqlConstants.KEY_SEQ);
	}
	public void setKeySequence(int keySequence) {
		set(SqlConstants.KEY_SEQ, keySequence);
	}
}
