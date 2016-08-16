package usf.java.sqlreflect.core.adapter;

import java.sql.SQLException;

import usf.java.sqlreflect.core.exception.AdapterException;
import usf.java.sqlreflect.core.reflect.ReflectorFactory;

public interface Transaction extends Adapter {
	
	void execute(ReflectorFactory rf) throws SQLException, AdapterException;

}
