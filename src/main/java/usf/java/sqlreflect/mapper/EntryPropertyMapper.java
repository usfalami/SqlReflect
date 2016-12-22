package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Header;

public class EntryPropertyMapper<T extends Entry> implements PropertyMapper<T> {

	@Override
	public void prepare(List<Header> headers) throws SQLException {
		
	}

	@Override
	public void setProperty(T obj, String propertyName, Object value) throws Exception {
		obj.set(propertyName, value);
	}

}
