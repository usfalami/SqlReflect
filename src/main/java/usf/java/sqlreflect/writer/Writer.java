package usf.java.sqlreflect.writer;

import java.util.Collection;

import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.Handler;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> extends Handler {
	

	//TODO replace Writer & Builder by property
	
	 void prepare(Class<? extends T> derivedClass, Collection<Property> properties) throws Exception;
	
	void write(StreamWriter writer, T obj) throws Exception;
	
}
