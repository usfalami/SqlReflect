package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.mapper.TimePerformMapper;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.stream.StreamWriter;

public class TimePerformWriter<T> implements ScannerAdapter<T>  {
	
	private StreamWriter writer;
	private Mapper<TimePerform> mapper;
	
	public TimePerformWriter(StreamWriter writer) {
		this.writer = writer;
	}

	@Override
	public void start() throws Exception {
		writer.start("LIST");
	}
	
	@Override
	public void prepare(Mapper<T> m) throws Exception {
		mapper = new TimePerformMapper();
		writer.startList("LIST", mapper.getColumnNames());
	}

	@Override
	public void adapte(T field, int index) throws Exception {
		//mapper.write(writer, field);
	}

	@Override
	public void end(TimePerform time) throws Exception {
		mapper.write(writer, time);
		writer.endList();
		writer.end();
	}
	
	public StreamWriter getWriter() {
		return writer;
	}
	
}