package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.ActionTimerMapper;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerWriter<T> implements Adapter<T> {
	
	private StreamWriter writer;
	private Mapper<ActionTimer> mapper;
	
	public ActionTimerWriter(StreamWriter writer) {
		this.writer = writer;
		mapper = new ActionTimerMapper();
	}

	@Override
	public void start() throws Exception {
		writer.start("LIST");
		writer.startList("LIST", mapper.getColumnNames());
	}
	
	@Override
	public void prepare(Mapper<T> mapper) throws Exception {
		//
	}

	@Override
	public void adapte(T field, int index) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void end(ActionTimer time) throws Exception {
		mapper.write(writer, time);
		writer.endList();
		writer.end();
	}
	
	public StreamWriter getWriter() {
		return writer;
	}
}