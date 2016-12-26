package usf.java.sqlreflect.writer;

import java.util.Collection;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.Handler;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> extends Handler {
	
	 <D extends T> void prepare(Class<D> derivedClass, Collection<Property> properties) throws Exception;
	
	void write(StreamWriter writer, T obj) throws Exception;
	
}
