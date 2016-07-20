package usf.java.sql.core.stream;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public interface StreamWriter {
	
	void writeBoolean(String name, boolean bool) throws Exception;
	void writeInt(String name, int number) throws Exception;
	void writeLong(String name, long number) throws Exception;
	void writeFloat(String name, float number) throws Exception;
	void writeDouble(String name, double number) throws Exception;
	void writeString(String name, String string) throws Exception;
	void writeDate(String name, Date date) throws Exception;

	void start(String name) throws Exception;
	void end() throws Exception;

	void startList(String name, String... columns) throws Exception;
	void endList() throws Exception;

	void startObject(String name) throws Exception;
	void endObject() throws Exception;
	

	DateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	DateFormat TIME_FORMATTER = new SimpleDateFormat("HH:mm:ss.SSS");

}
