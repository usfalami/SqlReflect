package usf.java.sqlreflect.writer;

import usf.java.sqlreflect.mapper.ComplexObject;
import usf.java.sqlreflect.reflect.Handler;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> extends Handler {
	

	//TODO replace Writer & Builder by property
	
	 void prepare(ComplexObject<? extends T> complexObjects) throws Exception;
	
	void write(StreamWriter writer, T obj) throws Exception;
	
}
