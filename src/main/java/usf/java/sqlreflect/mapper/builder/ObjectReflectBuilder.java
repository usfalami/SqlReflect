package usf.java.sqlreflect.mapper.builder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.Property;

public class ObjectReflectBuilder implements Builder<Object> {
	
	private Map<String, Method> methodMap;
	
	public ObjectReflectBuilder() {
		methodMap = new HashMap<String, Method>();
	}

	@Override
	public <D extends Object> void prepare(Class<D> derivedClass, Property property) throws Exception {
		String name = property.getName();
		Class<?> argClass = Class.forName(property.getClassName());
		Method method = derivedClass.getMethod(Utils.setterOf(name), argClass);
		methodMap.put(name, method);
	}

	@Override
	public void set(Object obj, String propertyName, Object value) throws Exception {
		methodMap.get(propertyName).invoke(obj, value);
	}

}
