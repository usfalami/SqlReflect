package usf.java.sqlreflect.sql.entry.item;

import usf.java.sqlreflect.SqlConstants;
import usf.java.sqlreflect.sql.entry.Entry;

public class Database extends Entry implements Item {

	public String getName() {
		return getString(SqlConstants.DATABASE_NAME);
	}

	public void setName(String name) {
		set(SqlConstants.DATABASE_NAME, name);
	}
	
}
