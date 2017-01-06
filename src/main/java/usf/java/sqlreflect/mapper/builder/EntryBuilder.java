package usf.java.sqlreflect.mapper.builder;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryBuilder implements Builder {
	
	@Override
	public void prepare(Class<?> derivedClass, Property property) {
		
	}

	@Override
	public void set(Object obj, Property property, Object value) {
		((Entry)obj).set(property.getName(), value);
	}

}
