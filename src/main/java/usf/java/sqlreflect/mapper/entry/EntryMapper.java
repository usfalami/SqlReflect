package usf.java.sqlreflect.mapper.entry;

import usf.java.sqlreflect.mapper.EntryTemplate;
import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryMapper extends SimpleObjectMapper<Entry> {

	public EntryMapper() {
		super(new EntryTemplate<Entry>(Entry.class));
	}

}
