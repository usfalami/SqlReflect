package usf.java.reflect;

import java.sql.SQLException;

import usf.java.adapter.AbstractAdapter;

public interface Reflector<T extends AbstractAdapter> {

	public abstract void run(T adapter) throws SQLException ;
	
}
