package usf.java.sqlreflect.mapper;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public abstract class Template<T> implements Generic<T> {
	
	protected String name;
	protected Class<T> type;
	
	private Method parentSetter, parentGetter;

	public Template(String name) {
		this.name = name;
	}
	public Template(Class<T> type) {
		this.type = type;
	}
	protected Template(String name, Class<T> type) {
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
	
	protected void setAccessorsFrom(Class<?> parentClass) throws Exception {
		parentGetter = parentClass.getMethod(Utils.getterOf(name));
		if(Utils.isNull(type)) type = (Class<T>) parentGetter.getReturnType();
		parentSetter = parentClass.getMethod(Utils.setterOf(name), type);
	}
	
	public void prepare(ResultSetMetaData rm) throws Exception {
		int count = rm.getColumnCount();
		Map<String, Header> map = new HashMap<String, Header>();
		for(int i=1; i<=count; i++){
			Header header = new Header();
			header.setColumnName(rm.getColumnName(i));
			header.setColumnClassName(rm.getColumnClassName(i));
			map.put(header.getColumnName(), header);
		}
		prepare(map);
	}
	
	protected void setValue(Object parent, Object value) throws Exception {
		parentSetter.invoke(parent, value);
	}
	protected Object getValue(Object parent) throws Exception {
		return parentGetter.invoke(parent);
	}
	
	protected void update(Object o, ResultSet rs) throws Exception {}
	
	protected abstract void prepare(Map<String, Header> map) throws Exception; 

	public abstract List<Template<?>> getFields();
	
	public abstract T map(ResultSet rs) throws Exception;
	
	public abstract void write(StreamWriter sw, T obj) throws Exception;
	
}
