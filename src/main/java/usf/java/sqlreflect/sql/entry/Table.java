package usf.java.sqlreflect.sql.entry;

import java.util.Collection;

import usf.java.sqlreflect.SqlConstants;

public class Table extends GenericType {

	private Collection<Column> columns;
	
	public String getDatabaseName() {
		return getString(SqlConstants.DATABASE_NAME);
	}
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.DATABASE_NAME, databaseName);
	}
	public String getName() {
		return getString(SqlConstants.TABLE_NAME);
	}
	public void setName(String name) {
		set(SqlConstants.TABLE_NAME, name);
	}
	public String getType() {
		return getString(SqlConstants.TABLE_TYPE);
	}
	public void setType(String type) {
		set(SqlConstants.TABLE_TYPE, type);
	}
	public Collection<Column> getColumnNames() {
		return columns;
	}
	public void setColumns(Collection<Column> columns) {
		this.columns = columns;
	}

}
