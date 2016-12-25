package usf.java.sqlreflect.mapper.builder;

import java.sql.SQLException;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryBuilder implements Builder<Entry> {
	
	@Override
	public <D extends Entry> void prepareProperty(Class<D> derivedClass, Metadata metadata) throws SQLException {
		
	}

	@Override
	public void setProperty(Entry obj, String propertyName, Object value) throws Exception {
		obj.set(propertyName, value);
	}

}
