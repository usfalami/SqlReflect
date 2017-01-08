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
	protected void prepare(Map<String, Header> map) throws Exception {
		super.prepare(map);
		field.prepare(map);
	}

	@Override
	public T map(ResultSet rs) throws Exception {
		T list = type.newInstance();
		list.add(field.map(rs));
		return list;
	}
	
	@Override
	protected void update(Object parent, ResultSet rs) throws Exception {
		T list = (T) getValue(parent);
		list.add(field.map(rs));
	}

}
