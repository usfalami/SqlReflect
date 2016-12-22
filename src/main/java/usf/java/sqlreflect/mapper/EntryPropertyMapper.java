package usf.java.sqlreflect.mapper;

import java.util.Collection;

import usf.java.sqlreflect.sql.entry.Entry;

public class EntryPropertyMapper<T extends Entry> implements PropertyMapper<T> {

	@Override
	public void prepare(Collection<Filter> headers) throws Exception {
		
	}

	@Override
	public void setProperty(T obj, String propertyName, Object value) throws Exception {
		obj.set(propertyName, value);
	}

}
