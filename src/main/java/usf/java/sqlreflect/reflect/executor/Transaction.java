package usf.java.sqlreflect.reflect.executor;

import usf.java.sqlreflect.reflect.ReflectorFactory;
import usf.java.sqlreflect.reflect.TimePerform;

public interface Transaction {
	
	void execute(ReflectorFactory rf, TimePerform tp) throws Exception;

}
