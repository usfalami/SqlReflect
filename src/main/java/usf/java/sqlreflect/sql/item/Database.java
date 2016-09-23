package usf.java.sqlreflect.sql.item;

import usf.java.sqlreflect.SqlConstants;

public class Database extends Entry {

	public String getName() {
		return getString(SqlConstants.TABLE_SCHEM);
	}

	public void setName(String name) {
		set(SqlConstants.TABLE_SCHEM, name);
	}
	
}
