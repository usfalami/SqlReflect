package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryMapperHandler<T extends Entry> implements MapperHandler<T> {

	@Override
	public void prepare(Collection<Metadata> headers) throws SQLException {
		
	}

	@Override
	public void setProperty(T obj, String propertyName, Object value) throws Exception {
		obj.set(propertyName, value);
	}

}
