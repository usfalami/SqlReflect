package usf.java.sqlreflect.mapper.builder;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.Handler;

public interface Builder<C> extends Handler {

	 <D extends C> void prepare(Class<D> derivedClass, Property peoperty) throws Exception;
	
	 void set(C obj, String propertyName, Object value) throws Exception;

}
