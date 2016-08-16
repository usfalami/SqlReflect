package usf.java.sqlreflect.reflect.performer;

import usf.java.sqlreflect.adapter.PerformAdapter;
import usf.java.sqlreflect.reflect.Reflector;

public interface Performer extends Reflector<PerformAdapter> {

	TimePerform run() throws Exception;
	
}
