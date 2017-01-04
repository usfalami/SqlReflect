package usf.java.sqlreflect.mapper.builder;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryBuilder implements Builder<Entry> {
	
	@Override
	public void prepare(Class<? extends Entry> derivedClass, Property property) {
		
	}

	@Override
	public void set(Entry obj, Property property, Object value) {
		obj.set(property.getName(), value);
	}

}
