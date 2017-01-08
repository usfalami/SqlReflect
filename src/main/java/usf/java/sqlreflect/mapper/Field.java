package usf.java.sqlreflect.mapper;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;

public abstract class Field<T> implements Generic<T> {

	protected String name;
	protected Class<T> type;
	
	private Method parentSetter, parentGetter;
	
	public Field(String name) {
		this.name = name;
	}
	public Field(String name, Class<T> type) {
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<T> getType() {
		return type;
	}
	public void setType(Class<T> type) {
		this.type = type;
	}
	
	public void prepare(Class<?> parentClass, Map<String, Header> headers) throws Exception {
		if(Utils.isNotNull(parentClass)){
			parentGetter = parentClass.getMethod(Utils.getterOf(name));
			if(Utils.isNotNull(type))
				type = (Class<T>) parentGetter.getReturnType();
			parentSetter = parentClass.getMethod(Utils.setterOf(name), type);
		}
	}

	protected void update(Object o, ResultSet rs) throws Exception {}
	
	public void setValue(Object parent, Object value) throws Exception {
		parentSetter.invoke(parent, value);
	}
	public Object getValue(Object parent) throws Exception {
		return parentGetter.invoke(parent);
	}
}
