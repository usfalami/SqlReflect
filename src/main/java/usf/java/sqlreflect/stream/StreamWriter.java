package usf.java.sqlreflect.stream;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;

import usf.java.sqlreflect.mapper.Property;

public interface StreamWriter {
	
	void writeBoolean(String name, Boolean bool) throws Exception;
	void writeInt(String name, Integer number) throws Exception;
	void writeLong(String name, Long number) throws Exception;
	void writeFloat(String name, Float number) throws Exception;
	void writeDouble(String name, Double number) throws Exception;
	void writeString(String name, String string) throws Exception;
	void writeDate(String name, Date date) throws Exception;

	void startObject(String name) throws Exception;
	void endObject() throws Exception;
	
	void startList(String name, Collection<Property> list) throws Exception;
	void endList() throws Exception;

	void start() throws Exception;
	void end() throws Exception;

	DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");

}
