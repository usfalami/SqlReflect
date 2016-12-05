package usf.java.sqlreflect.writer;

import java.util.Map;

import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> {
	
	void prepare(Map<String, String> columnTypes);
	
	void write(StreamWriter writer, T obj) throws Exception;

}
