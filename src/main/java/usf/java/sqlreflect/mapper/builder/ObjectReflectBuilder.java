package usf.java.sqlreflect.mapper.builder;

import java.lang.reflect.Method;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.Property;

public class ObjectReflectBuilder implements Builder {

	@Override
	public void prepare(Class<?> derivedClass, Property property) throws Exception {
		String name = property.getName();
		Class<?> argClass = Class.forName(property.getClassName());
		Method method = derivedClass.getMethod(Utils.setterOf(name), argClass);
		property.setField(Constants.PROPERTY_SETTER, method);
	}

	@Override
	public void set(Object obj, Property property, Object value) throws Exception {
		Method method = property.getField(Constants.PROPERTY_SETTER);
		method.invoke(obj, value);
	}

}
