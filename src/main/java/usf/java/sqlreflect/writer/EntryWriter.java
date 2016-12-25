package usf.java.sqlreflect.writer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryWriter implements Writer<Entry> {
	
	private Map<String, TypeWriter> types;

	@Override
	public<D extends Entry> void prepare(Class<D> derivedClass, Collection<Metadata> metadata) {
		types = new HashMap<String, TypeWriter>();
		for(Metadata header : metadata)
			types.put(header.getPropertyName(), TypeWriter.writerfor(header.getColumnClassName()));
	}

	@Override
	public void write(StreamWriter writer, Entry obj) throws Exception {
		for(java.util.Map.Entry<String, TypeWriter> entry : types.entrySet()) {
			String propertyName = entry.getKey();
			entry.getValue().write(writer, propertyName, obj.get(propertyName));
		}
	}

}