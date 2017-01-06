package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.util.Collection;

public class ListProperty<T extends Collection<C>, C> extends Field<T> {

	private Field<C> field;

	@Override
	public T get(ResultSet rs) throws Exception {
		T list = type.newInstance();
		list.add(field.get(rs));
		return list;
	}

	@Override
	public void update(Object o, ResultSet rs) throws Exception {
		Collection<C> list = (Collection<C>) getterMethod.invoke(o);
		list.add(field.get(rs));
	}
	
	@Override
	public void prepare(Class<?> parentClass) throws Exception {
		super.prepare(parentClass);
		field.prepare(null);
	}
}
