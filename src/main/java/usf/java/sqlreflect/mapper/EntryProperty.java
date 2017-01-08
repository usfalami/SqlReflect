package usf.java.sqlreflect.mapper;

import java.util.Map;

import usf.java.sqlreflect.mapper.converter.Converter;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Header;

public class EntryProperty<T> extends SimpleProperty<T> {
	
	public EntryProperty(String columnName) {
		super(columnName, columnName);
	}
	public EntryProperty(String name, String columnName) {
		super(name, columnName);
	}
	public EntryProperty(String columnName, Converter<? extends T> converter) {
		super(columnName, columnName, converter);
	}

	@Override
	public void prepare(Class<?> parentClass, Map<String, Header> headers) throws Exception {
		super.prepare(null, headers);
	}
		
	@Override
	public void setValue(Object parent, Object value) throws Exception {
		((Entry)parent).set(name, value);
	}
	
	@Override
	public Object getValue(Object parent) throws Exception {
		return ((Entry)parent).get(name);
	}
}
