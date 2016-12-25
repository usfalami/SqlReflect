package usf.java.sqlreflect.mapper.builder;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.Utils;

public class ObjectReflectBuild implements Builder<Object> {
	
	private Map<String, Method> methodMap;
	
	public ObjectReflectBuild() {
		methodMap = new HashMap<String, Method>();
	}

	@Override
	public <D extends Object> void prepareProperty(Class<D> derivedClass, Metadata metadata) throws Exception {
		String name = metadata.getPropertyName();
		Class<?> clazz = Class.forName(metadata.getColumnClassName());
		Method method = derivedClass.getMethod(Utils.setterOf(name), clazz);
		methodMap.put(name, method);
	}

	@Override
	public void setProperty(Object obj, String propertyName, Object value) throws Exception {
		methodMap.get(propertyName).invoke(obj, value);
	}

}
