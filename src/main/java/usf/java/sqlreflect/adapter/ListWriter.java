package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class ListWriter<T> implements Adapter<T> {

	private StreamWriter writer;
	private Mapper<T> mapper;

	public ListWriter(StreamWriter writer) {
		this.writer = writer;
	}

	@Override
	public void start() throws Exception { }
	
	@Override
	public void prepare(Mapper<T> mapper) throws Exception {
		this.mapper = mapper;
		writer.startList("Entries", mapper.getColumnNames());
	}

	@Override
	public void adapte(T field, int index) throws Exception {
		mapper.write(writer, field);
	}

	@Override
	public void end(ActionTimer time) throws Exception {
		writer.endList();
	}
	
	public StreamWriter getWriter() {
		return writer;
	}
	
}