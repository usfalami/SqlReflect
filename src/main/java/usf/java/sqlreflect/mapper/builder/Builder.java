package usf.java.sqlreflect.mapper.builder;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.Handler;

public interface Builder<T> extends Handler {

	void prepare(Collection<Metadata> metadata) throws SQLException;
	
	void setProperty(T obj, String propertyName, Object value) throws Exception;

}
