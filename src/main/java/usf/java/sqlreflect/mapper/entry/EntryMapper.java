package usf.java.sqlreflect.mapper.entry;

import usf.java.sqlreflect.mapper.EntryPropertyMapper;
import usf.java.sqlreflect.mapper.FiltredMapper;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryMapper extends FiltredMapper<Entry> {

	public EntryMapper(String... selectedColumnNames) {
		super(Entry.class, new EntryPropertyMapper<Entry>(), selectedColumnNames);
	}

}
