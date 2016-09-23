package usf.java.sqlreflect.sql.item;

import usf.java.sqlreflect.SqlConstants;

public class Procedure extends Callable {
	
	public Procedure() {
		super();
	}
	
	public Procedure(String callable) {
		super(callable);
	}

	@Override
	public String getDatabaseName() {
		return getString(SqlConstants.PROCEDURE_SCHEM);
	}
	@Override
	public void setDatabaseName(String databaseName) {
		set(SqlConstants.PROCEDURE_SCHEM, databaseName);
	}

	@Override
	public String getName() {
		return getString(SqlConstants.PROCEDURE_NAME);
	}
	@Override
	public void setName(String name) {
		set(SqlConstants.PROCEDURE_NAME, name);
	}

	@Override
	public String getType() {
		return getString(SqlConstants.PROCEDURE_TYPE);
	}
	@Override
	public void setType(String type) {
		set(SqlConstants.PROCEDURE_TYPE, type);
	}

}
