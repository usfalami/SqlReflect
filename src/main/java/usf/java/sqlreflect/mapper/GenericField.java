package usf.java.sqlreflect.mapper;

import java.lang.reflect.Method;
import java.util.Map;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;

public class GenericField<C, R> {

	protected String name;
	protected Class<R> type;
	protected Map<String, Object> attributes;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<R> getType() {
		return type;
	}
	public void setType(Class<R> type) {
		this.type = type;
	}
	
	public void prepare(Class<?> parentClass) throws Exception {
		Method method = parentClass.getMethod(Utils.setterOf(name), type);
		attributes.put(Constants.PROPERTY_SETTER, method);
	}
	public void set(Object parent, Object value) throws Exception {
		Method method = (Method) attributes.get(Constants.PROPERTY_SETTER);
		method.invoke(parent, value);
	}

}
