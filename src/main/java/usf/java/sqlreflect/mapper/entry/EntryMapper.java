package usf.java.sqlreflect.mapper.entry;

import usf.java.sqlreflect.mapper.EntryMapperHandler;
import usf.java.sqlreflect.mapper.GenericMapper;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryMapper extends GenericMapper<Entry> {

	public EntryMapper(String... selectedColumnNames) {
		super(Entry.class, new EntryMapperHandler<Entry>(), selectedColumnNames);
	}

}