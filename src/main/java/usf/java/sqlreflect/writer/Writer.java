package usf.java.sqlreflect.writer;

import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> {
	
	void write(StreamWriter writer, T obj) throws Exception;

}
