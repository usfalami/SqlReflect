package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.TimePerform;


public interface Adapter {
	
	void start() throws Exception;
	void end(TimePerform time)  throws Exception;

}
