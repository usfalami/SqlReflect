package usf.java.sqlreflect.writer;

import usf.java.sqlreflect.reflect.Handler;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> extends Handler {
	
	void write(StreamWriter writer, T obj) throws Exception;
	
}
