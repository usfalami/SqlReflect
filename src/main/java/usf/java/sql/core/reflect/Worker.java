package usf.java.sql.core.reflect;

import java.sql.SQLException;

import usf.java.sql.core.exception.AdapterException;

public interface Worker<T extends Worker.Adapter> {
	
	void run(T adapter) throws SQLException, AdapterException;
	
	public static interface Adapter {
		
		void start() throws AdapterException;
		void end()  throws AdapterException;
	
	}

}
