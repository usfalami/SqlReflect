package usf.java.sqlreflect.sql.item;

import java.util.Collection;
import java.util.List;

import usf.java.sqlreflect.SqlConstants;

public class Table extends Entry {

	private Collection<Column> columns;
	
	public String getDatabaseName() {
		return getString(SqlConstants.TABLE_SCHEM);
	}
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.TABLE_SCHEM, databaseName);
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
	public Collection<Column> getColumns() {
		return columns;
	}
	public void setColumns(Collection<Column> columns) {
		this.columns = columns;
	}

}
