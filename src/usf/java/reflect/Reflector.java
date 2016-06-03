package usf.java.reflect;

import java.sql.SQLException;

import usf.java.adapter.Adapter;

public interface Reflector<T extends Adapter> {

	public abstract void run(T adapter) throws SQLException ;
	
}
