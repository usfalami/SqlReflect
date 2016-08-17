package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.ReflectorFactory;

public interface Transaction extends Adapter<Void> {
	
	void execute(ReflectorFactory rf) throws Exception;

}
