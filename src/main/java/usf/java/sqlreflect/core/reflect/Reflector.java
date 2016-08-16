package usf.java.sqlreflect.core.reflect;

import java.sql.SQLException;

import usf.java.sqlreflect.core.adapter.Adapter;
import usf.java.sqlreflect.core.exception.AdapterException;

public interface Reflector<T extends Adapter> {
	
	void run(T adapter) throws SQLException, AdapterException;
	
}
