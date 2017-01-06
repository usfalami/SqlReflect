package usf.java.sqlreflect.mapper;

import java.lang.reflect.Method;
import java.sql.ResultSet;

import usf.java.sqlreflect.Utils;

public abstract class Field<T> implements Generic<T> {

	protected String name;
	protected Class<T> type;
	protected Method setterMethod, getterMethod;
	
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
	
	public void prepare(Class<?> parentClass) throws Exception {
		if(Utils.isNotNull(parentClass)){
			setterMethod = parentClass.getMethod(Utils.setterOf(name), type);
			getterMethod = parentClass.getMethod(Utils.getterOf(name));
		}
	}

	protected void update(Object o, ResultSet rs) throws Exception {}
	
	protected void set(Object parent, Object value) throws Exception {
		setterMethod.invoke(parent, value);
	}
}
