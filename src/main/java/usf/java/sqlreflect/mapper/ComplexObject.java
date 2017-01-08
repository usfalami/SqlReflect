package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.Map;

import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;

public class ComplexObject<T> extends ComplexProperty<T> {
	
	//TODO replace Writer & Builder by property
	protected SimpleProperty<?> id;
	
	private Map<Object, T> dataMap;
	private Generic<T> proxy;
	
	public ComplexObject(String name) {
		super(name);
	}
	public ComplexObject(String name, Class<T> type) {
		super(name, type);
	}

	@Override
	public void prepare(Map<String, Header> headers) throws Exception {
		super.prepare(headers);
		proxy = Utils.isNull(id) ? new NoIdMapperProxy() : new IdMapperProxy();
	}
	
	@Override
	public T map(ResultSet rs) throws Exception {
		return proxy.map(rs);
	}
	
	private class NoIdMapperProxy implements Generic<T> {
		@Override
		public T map(ResultSet rs) throws Exception {
			return ComplexObject.super.map(rs);
		}
	}
	private class IdMapperProxy implements Generic<T> {
		@Override
		public T map(ResultSet rs) throws Exception {
			Object key = id.map(rs);
			T obj = dataMap.get(key);
			if(Utils.isNull(obj)){
				obj = ComplexObject.super.map(rs);
				id.setValue(obj, key);
				dataMap.put(key, obj);
			}else{
				for(Template<?> field : fields)
					field.update(obj, rs);
			}
			return obj;
		}
	}

}
