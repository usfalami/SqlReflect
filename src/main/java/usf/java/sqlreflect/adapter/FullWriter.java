package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;
import usf.java.sqlreflect.writer.ActionTimerWriter;
import usf.java.sqlreflect.writer.Writer;

public class FullWriter<T> extends ListWriter<T> {
	
	public FullWriter(StreamWriter stream, Writer<T> writer) {
		super(stream, writer);
	}

	@Override
	public void start() throws Exception {
		getStream().startObject("ComplexType");
		super.start();
	}
	
	@Override
	public void end(ActionTimer time) throws Exception {
		super.end(time);
		Writer<ActionTimer> writer = new ActionTimerWriter();
		writer.write(getStream(), time);
		getStream().endObject();
	}
}