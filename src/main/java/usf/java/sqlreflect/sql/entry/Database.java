package usf.java.sqlreflect.sql.entry;

import usf.java.sqlreflect.SqlConstants;

public class Database extends Entry {

	public String getName() {
		return getString(SqlConstants.DATABASE_NAME);
	}

	public void setName(String name) {
		set(SqlConstants.DATABASE_NAME, name);
	}
	
}
