package usf.java.sql.core.reflect;

import java.sql.SQLException;

import usf.java.sql.core.adapter.Adapter;
import usf.java.sql.core.exception.AdapterException;

public interface Worker<T extends Adapter> {
	
	void run(T adapter) throws SQLException, AdapterException;
	
}
