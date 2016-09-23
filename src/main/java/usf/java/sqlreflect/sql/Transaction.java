package usf.java.sqlreflect.sql;

import usf.java.sqlreflect.reflect.ReflectorFactory;

public interface Transaction {
	
	void execute(ReflectorFactory rf) throws Exception;

}
