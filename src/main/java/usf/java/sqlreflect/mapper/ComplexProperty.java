package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;

public class ComplexProperty<T> extends Field<T> {
	
	//TODO replace Writer & Builder by property
	private SimpleProperty<?> id;
	private List<Field<?>> fields;
	
	private Map<Object, T> dataMap;
	private Generic<T> proxy;
	
	public ComplexProperty(String name) {
		super(name);
		this.fields = new ArrayList<Field<?>>();
	}
	public ComplexProperty(String name, Class<T> type) {
		super(name, type);
		this.fields = new ArrayList<Field<?>>();
	}
	public ComplexProperty(String name, List<Field<?>> fields) {
		super(name);
		this.fields = fields;
	}
	public ComplexProperty(String name, Class<T> type, List<Field<?>> fields) {
		super(name, type);
		this.fields = fields;
	}

	public void setFields(List<Field<?>> fields) {
		this.fields = fields;
	}
	public List<Field<?>> getFields() {
		return fields;
	}
	
	@Override
	public void prepare(Class<?> parentClass, Map<String, Header> headers) throws Exception {
		super.prepare(parentClass, headers);
		proxy = Utils.isNull(id) ? new NoIdMapperProxy() : new IdMapperProxy();
		for(Field<?> field : fields)
			field.prepare(type, headers);
	}
	
	@Override
	public T get(ResultSet rs) throws Exception {
		return proxy.get(rs);
	}
	
	private class NoIdMapperProxy implements Generic<T> {
		
		@Override
		public T get(ResultSet rs) throws Exception {
			T obj = type.newInstance();
			for(Field<?> field : fields){
				Object value = field.get(rs);
				field.setValue(obj, value);
			}
			return obj;
		}
	}
	private class IdMapperProxy implements Generic<T> {

		@Override
		public T get(ResultSet rs) throws Exception {
			Object key = id.get(rs);
			T obj = dataMap.get(key);
			if(Utils.isNull(obj)){
				obj = noIdMapperProxy.get(rs);
				id.setValue(obj, key);
				dataMap.put(key, obj);
			}else{
				for(Field<?> field : fields)
					field.update(obj, rs);
			}
			return obj;
		}
		private Generic<T> noIdMapperProxy = new NoIdMapperProxy();
		
		public IdMapperProxy() {
			dataMap = new HashMap<Object, T>();
		}
	}

}
