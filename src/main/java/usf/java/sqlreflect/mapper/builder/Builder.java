package usf.java.sqlreflect.mapper.builder;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.Handler;

public interface Builder<C> extends Handler {

	 <D extends C> void prepare(Class<D> derivedClass, Property property) throws Exception;
	
	 void set(C obj, Property property, Object value) throws Exception;

}
