package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.ReflectorFactory;

public interface Transaction extends Adapter {
	
	void execute(ReflectorFactory rf) throws Exception;

}
