package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;
import usf.java.sqlreflect.writer.Writer;

public class ListWriter<T> implements Adapter<T> {

	private StreamWriter stream;
	private Writer<T> writer;

	public ListWriter(StreamWriter stream, Writer<T> writer) {
		this.stream = stream;
		this.writer = writer;
	}

	@Override
	public void start() throws Exception { }
	
	@Override
	public void prepare(Mapper<T> mapper) throws Exception {
		writer.prepare(mapper);
		stream.startList("Entries", writer.getColumnNames());
	}

	@Override
	public void adapte(T field, int index) throws Exception {
		writer.write(stream, field);
	}

	@Override
	public void end(ActionTimer time) throws Exception {
		stream.endList();
	}
	
	public StreamWriter getStream() {
		return stream;
	}
	
}