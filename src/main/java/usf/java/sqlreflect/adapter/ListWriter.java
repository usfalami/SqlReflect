package usf.java.sqlreflect.adapter;

import java.util.Collection;

import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;
import usf.java.sqlreflect.writer.Writer;

public class ListWriter<T> implements Adapter<T> {

	private StreamWriter stream;
	private Writer<? super T> writer;

	public ListWriter(StreamWriter stream, Writer<? super T> writer) {
		this.stream = stream;
		this.writer = writer;
	}

	@Override
	public void start() throws Exception { }
	
	@Override
	public void prepare(Collection<Metadata> metadata, Class<T> clazz) throws Exception {
		writer.prepare(metadata);
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