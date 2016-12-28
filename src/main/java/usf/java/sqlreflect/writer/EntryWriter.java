package usf.java.sqlreflect.writer;

import java.util.Collection;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryWriter implements Writer<Entry> {
	
	private Collection<Property> properties;

	@Override
	public<D extends Entry> void prepare(Class<D> derivedClass, Collection<Property> properties) {
		this.properties = properties;
		for(Property property : properties) {
			WriterTypes tw = WriterTypes.writerfor(property.getClassName());
			property.setField("writer", tw);
		}
	}

	@Override
	public void write(StreamWriter writer, Entry obj) throws Exception {
		for(Property property : properties){
			WriterTypes type = property.getField("writer");
			String name = property.getName();
			type.write(writer, name, obj.get(name));
		}
	}

}