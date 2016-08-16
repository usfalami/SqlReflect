package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.reflect.performer.TimePerform;
import usf.java.sqlreflect.stream.StreamWriter;

public class TimePerformWriter extends TimePerformAdapter  {

	private StreamWriter writer;
	private Mapper<TimePerform> mapper;
	
	public TimePerformWriter(Mapper<TimePerform> mapper, StreamWriter writer) {
		super();
		this.writer = writer;
		this.mapper = mapper;
	}

	@Override
	public void end() throws Exception {
		super.end();
		writer.start("LIST");
		writer.startList("LIST", mapper.getColumnNames());
		mapper.write(writer, time);
		writer.endList();
		writer.end();
	}
	
}