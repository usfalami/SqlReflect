package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import usf.java.sqlreflect.sql.entry.Header;

public class ListProperty<T extends Collection<C>, C> extends Field<T> {

	private Field<C> field;
	
	public ListProperty(String name, Field<C> field) {
		super(name);
		this.field = field;
	}

	@Override
	public void prepare(Class<?> parentClass, Map<String, Header> map) throws Exception {
		super.prepare(parentClass, map);
		field.prepare(null, map);
	}

	@Override
	public T get(ResultSet rs) throws Exception {
		T list = type.newInstance();
		list.add(field.get(rs));
		return list;
	}
	
	@Override
	public void update(Object parent, ResultSet rs) throws Exception {
		T list = (T) getValue(parent);
		list.add(field.get(rs));
	}

}
