package usf.java.sqlreflect.stream;

import java.sql.Date;

public class DebugProxyStream<T extends StreamWriter> implements StreamWriter {
	
	private StreamWriter sw;
	private int level = 0;
	
	public DebugProxyStream(T sw) {
		this.sw = sw;
	}

	@Override
	public void writeBoolean(String name, Boolean bool) throws Exception {
		sw.writeBoolean(name, bool);
		debug(level+1, bool);
	}

	@Override
	public void writeInt(String name, Integer number) throws Exception {
		sw.writeInt(name, number);
		debug(level+1, number);
	}

	@Override
	public void writeLong(String name, Long number) throws Exception {
		sw.writeLong(name, number);
		debug(level+1, number);
	}

	@Override
	public void writeFloat(String name, Float number) throws Exception {
		sw.writeFloat(name, number);
		debug(level+1, number);
	}

	@Override
	public void writeDouble(String name, Double number) throws Exception {
		sw.writeDouble(name, number);
		debug(level+1, number);
	}

	@Override
	public void writeString(String name, String string) throws Exception {
		sw.writeString(name, string);
		debug(level+1, string);
	}

	@Override
	public void writeDate(String name, Date date) throws Exception {
		sw.writeDate(name, date);
		debug(level+1, name);
	}

	@Override
	public void startObject(String name) throws Exception {
		sw.startObject(name);
		debug(++level, "{" + name+ "}");
	}

	@Override
	public void endObject() throws Exception {
		sw.endObject();
		debug(level--, "End");
	}

	@Override
	public void startList(String name, String... columns) throws Exception {
		sw.startList(name, columns);
		debug(++level, "[" + name + "]");
	}

	@Override
	public void endList() throws Exception {
		sw.endList();
		debug(level--, "End");
	}

	@Override
	public void start() throws Exception {
		sw.start();
		debug(0, "Root");		
	}

	@Override
	public void end() throws Exception {
		sw.end();
		debug(0, "End");
	}
	
	
	private void debug(int level, Object text) {
		System.out.println(level == 0 ? text : 
			String.format("%" + level + "s", "").replace(" ", " ¦ ....") + text);
	}

}
