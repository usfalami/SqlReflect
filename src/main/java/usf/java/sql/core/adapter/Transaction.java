package usf.java.sql.core.adapter;

import java.sql.SQLException;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.reflect.ReflectorFactory;

public interface Transaction extends Adapter {
	
	void execute(ReflectorFactory rf) throws SQLException, AdapterException;

}
