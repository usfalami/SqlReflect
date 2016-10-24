package usf.java.sqlreflect.sql.entry.item;

import usf.java.sqlreflect.SqlConstants;

public class Function extends Callable {

	@Override
	public String getDatabaseName() {
		return getString(SqlConstants.DATABASE_NAME);
	}
	@Override
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.DATABASE_NAME, databaseName);
	}

	@Override
	public String getName() {
		return getString(SqlConstants.FUNCTION_NAME);
	}
	@Override
	public void setName(String name) {
		set(SqlConstants.FUNCTION_NAME, name);
	}

	@Override
	public String getType() {
		return getString(SqlConstants.FUNCTION_TYPE);
	}
	@Override
	public void setType(String type) {
		set(SqlConstants.FUNCTION_TYPE, type);
	}

}
