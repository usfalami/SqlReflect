package usf.java.sqlreflect.mapper.builder;

import java.sql.SQLException;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.Handler;

public interface Builder<C> extends Handler {

	 <D extends C> void prepareProperty(Class<D> derivedClass, Metadata metadata) throws SQLException;
	
	 void setProperty(C obj, String propertyName, Object value) throws Exception;

}
