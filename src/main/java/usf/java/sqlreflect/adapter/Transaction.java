package usf.java.sqlreflect.adapter;

import java.sql.SQLException;

import usf.java.sqlreflect.exception.AdapterException;
import usf.java.sqlreflect.reflect.ReflectorFactory;

public interface Transaction extends Adapter {
	
	void execute(ReflectorFactory rf) throws SQLException, AdapterException;

}
