package usf.java.sqlreflect.writer;

import java.sql.SQLException;
import java.util.List;

import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> {
	
	void prepare(List<Header> headers) throws SQLException;
	
	void write(StreamWriter writer, T obj) throws Exception;
	
	String[] getColumnNames();
	
}
