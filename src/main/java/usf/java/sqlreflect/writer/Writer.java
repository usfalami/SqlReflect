package usf.java.sqlreflect.writer;

import java.sql.SQLException;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.stream.StreamWriter;

public interface Writer<T> {
	
	void prepare(Mapper<T> mapper) throws SQLException;
	
	String[] getSelectedColumns();
	
	void write(StreamWriter writer, T obj) throws Exception;
	
	

}
