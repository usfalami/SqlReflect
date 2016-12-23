package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.sql.entry.Entry;

public class EntryHandler<T extends Entry> implements BeanHandler<T> {
	
	private Class<T> className;
	
	public EntryHandler(Class<T> className) {
		this.className = className;
	}

	@Override
	public void prepare(Collection<Metadata> headers) throws SQLException {
		
	}

	@Override
	public void setProperty(T obj, String propertyName, Object value) throws Exception {
		obj.set(propertyName, value);
	}
	
	@Override
	public Class<T> getBeanClass() {
		return className;
	}

}
