package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.reflect.ReflectorFactory;

public interface Transaction {
	
	void execute(ReflectorFactory rf) throws Exception;

}
