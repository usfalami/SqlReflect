package usf.java.sql.core.reflect;

import usf.java.sql.core.connection.ConnectionManager;

public abstract class Reflector {
	
	protected ConnectionManager cm;
	
	public Reflector(ConnectionManager cm) {
		this.cm = cm;
	}

}
