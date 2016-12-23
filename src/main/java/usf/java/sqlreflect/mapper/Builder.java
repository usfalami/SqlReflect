package usf.java.sqlreflect.mapper;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.reflect.Handler;

public interface Builder<T> extends Handler {

	void prepare(Collection<Metadata> headers) throws SQLException;
	
	void setProperty(T obj, String propertyName, Object value) throws Exception;

}
