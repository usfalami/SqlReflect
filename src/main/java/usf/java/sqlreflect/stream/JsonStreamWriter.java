package usf.java.sqlreflect.stream;

import java.io.Writer;
import java.sql.Date;
import java.util.Stack;

import org.json.JSONException;
import org.json.JSONWriter;

import usf.java.sqlreflect.mapper.Template;

public class JsonStreamWriter implements StreamWriter {
	
	protected JSONWriter jwriter;
	
	private Stack<Boolean> keys;
	
	public JsonStreamWriter(Writer writer) {
		jwriter = new JSONWriter(writer);
		keys = new Stack<Boolean>();
	}

	@Override
	public void writeBoolean(String name, Boolean bool) throws JSONException {
		jwriter.key(name).value(bool);
	}

	@Override
	public void writeInt(String name, Integer number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeLong(String name, Long number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeFloat(String name, Float number) throws JSONException {
		jwriter.key(name).value(number);
	}

	@Override
	public void writeDouble(String name, Double number) throws JSONException {
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
		key(name).object();
		keys.push(true);
	}
	@Override
	public void endObject() throws JSONException {
		jwriter.endObject();
		keys.pop();
	}
	
	@Override
	public void startList(String name, Template<?> complexObject) throws Exception {
		key(name).array();
		keys.push(false);
	}
	@Override
	public void endList() throws Exception {
		jwriter.endArray();
		keys.pop();
	}
	
	@Override
	public void start() throws Exception {
		jwriter.array();
		keys.push(false);
	}
	@Override
	public void end() throws Exception {
		jwriter.endArray();
		keys.pop();
	}

	
	protected JSONWriter key(String key) throws JSONException{
		return keys.peek() ? jwriter.key(key) : jwriter;
	}
	
}
