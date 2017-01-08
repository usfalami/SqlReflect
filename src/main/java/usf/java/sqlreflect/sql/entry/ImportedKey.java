package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class ImportedKey extends GenericType {
	
	
	public String getPrimaryKeyTable() {
		return getString(SqlConstants.PKTABLE_NAME);
	}
	public void setPrimaryKeyTable(String databaseName) {
		set(SqlConstants.PKTABLE_NAME, databaseName);
	}
	public String getPrimaryKeyColumn() {
		return getString(SqlConstants.PKCOLUMN_NAME);
	}
	public void setPrimaryKeyColumn(String tableName) {
		set(SqlConstants.PKCOLUMN_NAME, tableName);
	}
	public String getForeignKeyTable() {
		return getString(SqlConstants.FKTABLE_NAME);
	}
	public void setForeignKeyTable(String name) {
		set(SqlConstants.FKTABLE_NAME, name);
	}
	public String getForeignKeyColumn() {
		return getString(SqlConstants.FKCOLUMN_NAME);
	}
	public void setColumnForeignKeyColumn(String name) {
		set(SqlConstants.FKCOLUMN_NAME, name);
	}
	public String getPrimaryKeyName() {
		return getString(SqlConstants.PK_NAME);
	}
	public void setPrimaryKeyName(String name) {
		set(SqlConstants.PK_NAME, name);
	}
	public int getKeySequence() {
		return getInteger(SqlConstants.KEY_SEQ);
	}
	public void setKeySequence(int keySequence) {
		set(SqlConstants.KEY_SEQ, keySequence);
	}
	public String getForeignKeyName() {
		return getString(SqlConstants.FK_NAME);
	}
	public void setForeignKeyName(String name) {
		set(SqlConstants.FK_NAME, name);
	}

}
