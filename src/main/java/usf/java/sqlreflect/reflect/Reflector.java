package usf.java.sqlreflect.reflect;

import java.sql.SQLException;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.exception.AdapterException;

public interface Reflector<T extends Adapter> {
	
	void run(T adapter) throws SQLException, AdapterException;
	
}
