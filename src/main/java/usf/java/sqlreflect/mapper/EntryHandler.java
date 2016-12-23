package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryHandler implements BeanHandler<Entry> {
	
	@Override
	public void prepare(Collection<Metadata> headers) throws SQLException {
		
	}

	@Override
	public void setProperty(Entry obj, String propertyName, Object value) throws Exception {
		obj.set(propertyName, value);
	}

}
