package usf.java.sqlreflect.mapper.builder;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryBuilder implements Builder<Entry> {
	
	@Override
	public <D extends Entry> void prepare(Class<D> derivedClass, Property property) {
		
	}

	@Override
	public void set(Entry obj, String propertyName, Object value) {
		obj.set(propertyName, value);
	}

}
