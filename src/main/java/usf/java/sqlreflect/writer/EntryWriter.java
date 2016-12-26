package usf.java.sqlreflect.writer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryWriter implements Writer<Entry> {
	
	private Map<String, WriterTypes> types;

	@Override
	public<D extends Entry> void prepare(Class<D> derivedClass, Collection<Property> properties) {
		types = new HashMap<String, WriterTypes>();
		for(Property property : properties)
			types.put(property.getName(), WriterTypes.writerfor(property.getClassName()));
	}

	@Override
	public void write(StreamWriter writer, Entry obj) throws Exception {
		for(java.util.Map.Entry<String, WriterTypes> entry : types.entrySet()) {
			String propertyName = entry.getKey();
			entry.getValue().write(writer, propertyName, obj.get(propertyName));
		}
	}

}