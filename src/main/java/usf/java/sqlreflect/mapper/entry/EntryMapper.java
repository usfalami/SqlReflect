package usf.java.sqlreflect.mapper.entry;

import usf.java.sqlreflect.mapper.EntryPropertyMapper;
import usf.java.sqlreflect.mapper.SimpleMapper;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryMapper extends SimpleMapper<Entry> {

	public EntryMapper(String... selectedColumnNames) {
		super(Entry.class, new EntryPropertyMapper<Entry>(), selectedColumnNames);
	}

}
