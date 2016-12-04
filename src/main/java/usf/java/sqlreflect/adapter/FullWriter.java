package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.mapper.ActionTimerMapper;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;
import usf.java.sqlreflect.writer.Writer;

public class FullWriter<T> extends ListWriter<T> {
	
	public FullWriter(StreamWriter writer) {
		super(writer);
	}
	
	@Override
	public void start() throws Exception {
		getWriter().startObject("ComplexType");
		super.start();
	}
	
	@Override
	public void end(ActionTimer time) throws Exception {
		super.end(time);
		Writer<ActionTimer> writer = new ActionTimerMapper();
		writer.write(getWriter(), time);
		getWriter().endObject();
	}
}