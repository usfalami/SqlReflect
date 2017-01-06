package usf.java.sqlreflect.mapper.builder;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.Handler;

public interface Builder extends Handler {

	void prepare(Class<?> derivedClass, Property property) throws Exception;
	
	void set(Object obj, Property property, Object value) throws Exception;
	
}
