package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.Utils;

public class ComplexProperty<T> extends Field<T> {
	
	//TODO replace Writer & Builder by property
	private SimpleProperty<?> id;
	private List<Field<?>> fields;
	private Map<Object, T> map;
	
	private Generic<T> proxy;

	@Override
	public T get(ResultSet rs) throws Exception {
		return proxy.get(rs);
	}
	
	@Override
	public void prepare(Class<?> parentClass) throws Exception {
		super.prepare(parentClass);
		proxy = Utils.isNull(id) ? new NoIdMapperProxy() : new IdMapperProxy();
		for(Field<?> field : fields)
			field.prepare(type);
	}
	
	private class NoIdMapperProxy implements Generic<T> {
		@Override
		public T get(ResultSet rs) throws Exception {
			T obj = getType().newInstance();
			for(Field<?> field : fields){
				Object value = field.get(rs);
				field.set(obj, value);
			}
			return obj;
		}
	}
	private class IdMapperProxy implements Generic<T> {
		@Override
		public T get(ResultSet rs) throws Exception {
			Object key = id.get(rs);
			T obj = map.get(key);
			if(Utils.isNull(obj)){
				obj = new NoIdMapperProxy().get(rs);
				id.set(obj, key);
				map.put(key, obj);
			}else{
				for(Field<?> field : fields)
					field.update(obj, rs);
			}
			return obj;
		}
	}

}
