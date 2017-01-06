package usf.java.sqlreflect.mapper.entry;

import usf.java.sqlreflect.mapper.SimpleObjectMapper;
import usf.java.sqlreflect.mapper.builder.EntryBuilder;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryMapper extends SimpleObjectMapper<Entry> {

	public EntryMapper(String... selectedColumnNames) {
		super(Entry.class, new EntryBuilder(), selectedColumnNames);
	}

}
