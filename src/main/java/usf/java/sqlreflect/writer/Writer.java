package usf.java.sqlreflect.writer;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.mapper.filter.Metadata;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> {
	
	void prepare(Collection<Metadata> metadata) throws SQLException;
	
	void write(StreamWriter writer, T obj) throws Exception;
	
	String[] getColumnNames();
	
}
