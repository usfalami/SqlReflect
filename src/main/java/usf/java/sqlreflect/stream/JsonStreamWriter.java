package usf.java.sqlreflect.stream;

import java.io.Writer;
import java.sql.Date;

import org.json.JSONException;
import org.json.JSONWriter;

public class JsonStreamWriter implements StreamWriter {
	
	protected JSONWriter jwriter;
	
	public JsonStreamWriter(Writer writer) {
		jwriter = new JSONWriter(writer); 
	}

	@Override
	public void writeBoolean(String name, boolean bool) throws JSONException {
		jwriter.key(name).value(bool);
	}

	@Override
	public void writeInt(String name, int number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeLong(String name, long number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeFloat(String name, float number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeDouble(String name, double number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeString(String name, String string) throws JSONException {
		jwriter.key(name).value(string);
	}

	@Override
	public void writeDate(String name, Date date) throws JSONException {
		jwriter.key(name).value(DATE_FORMATTER.format(date));
	}

	@Override
	public void startObject(String name) throws JSONException {
		jwriter.object();
	}
	@Override
	public void endObject() throws JSONException {
		jwriter.endObject();
	}
	
	@Override
	public void startList(String name, String... columns) throws Exception {
		jwriter.array();
	}
	@Override
	public void endList() throws Exception {
		jwriter.endArray();
	}
	
	@Override
	public void start(String name) throws Exception {}
	@Override
	public void end() throws Exception {}
	
}
