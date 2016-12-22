package usf.java.sqlreflect.writer;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import usf.java.sqlreflect.sql.entry.Entry;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class EntryWriter<T extends Entry> implements Writer<T> {
	
	private Map<String, TypeWriter> types;

	@Override
	public void prepare(List<Header> headers) throws SQLException {
		types = new HashMap<String, TypeWriter>();
		for(Header header : headers)
			types.put(header.getPropertyName(), TypeWriter.writerfor(header.getColumnClassName()));
	}
	
	@Override
	public String[] getColumnNames() {
		return types.keySet().toArray(new String[types.size()]);
	}

	@Override
	public void write(StreamWriter writer, T obj) throws Exception {
		writer.startObject("");
		for(java.util.Map.Entry<String, TypeWriter> entry : types.entrySet()) {
			String propertyName = entry.getKey();
			entry.getValue().write(writer, propertyName, obj.get(propertyName));
		}
		writer.endObject();
	}

}