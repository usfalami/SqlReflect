package usf.java.sqlreflect.stream;

import java.io.Writer;
import java.sql.Date;

import org.json.JSONException;
import org.json.JSONWriter;

public class JsonStreamWriter implements StreamWriter {
	
	protected JSONWriter jwriter;
	
	int level = 0;
	
	public JsonStreamWriter(Writer writer) {
		jwriter = new JSONWriter(writer); 
	}

	@Override
	public void writeBoolean(String name, boolean bool) throws JSONException {
		jwriter.key(name).value(bool);
		debug(level+1, "value");
	}

	@Override
	public void writeInt(String name, int number) throws JSONException {
		jwriter.key(name).value(number);
		debug(level+1, "value");
	}

	@Override
	public void writeLong(String name, long number) throws JSONException {
		jwriter.key(name).value(number);
		debug(level+1, "value");
	}

	@Override
	public void writeFloat(String name, float number) throws JSONException {
		jwriter.key(name).value(number);
		debug(level+1, "value");
	}

	@Override
	public void writeDouble(String name, double number) throws JSONException {
		jwriter.key(name).value(number);
		debug(level+1, "value");
	}

	@Override
	public void writeString(String name, String string) throws JSONException {
		jwriter.key(name).value(string);
		debug(level+1, "value");
	}

	@Override
	public void writeDate(String name, Date date) throws JSONException {
		jwriter.key(name).value(DATE_FORMATTER.format(date));
		debug(level+1, "value");
	}

	@Override
	public void startObject(String name) throws JSONException {
		jwriter.object();
		debug(++level, "object");
	}
	@Override
	public void endObject() throws JSONException {
		jwriter.endObject();
		debug(level--, "end");
	}
	
	@Override
	public void startList(String name, String... columns) throws Exception {
		jwriter.key(name);
		jwriter.array();
		debug(++level, "array");
	}
	@Override
	public void endList() throws Exception {
		jwriter.endArray();
		debug(level--, "end");
	}
	
	@Override
	public void start() throws Exception {
		jwriter.array();
		debug(0, "array");
	}
	@Override
	public void end() throws Exception {
		jwriter.endArray();
		debug(0, "end");
	}
	
	private void debug(int level, String text) {
		if(level == 0) System.out.println(text);
		else System.out.println(String.format("%" + level + "s", "").replace(" ", "⁞....") + text);
	}
	
}
