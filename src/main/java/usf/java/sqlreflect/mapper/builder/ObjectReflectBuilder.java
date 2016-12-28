package usf.java.sqlreflect.mapper.builder;

import java.lang.reflect.Method;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.Property;

public class ObjectReflectBuilder implements Builder<Object> {

	@Override
	public <D extends Object> void prepare(Class<D> derivedClass, Property property) throws Exception {
		String name = property.getName();
		Class<?> argClass = Class.forName(property.getClassName());
		Method method = derivedClass.getMethod(Utils.setterOf(name), argClass);
		property.setField("setter", method);
	}

	@Override
	public void set(Object obj, Property property, Object value) throws Exception {
		Method method = property.getField("setter");
		method.invoke(obj, value);
	}

}
