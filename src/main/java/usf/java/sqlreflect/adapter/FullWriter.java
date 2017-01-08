package usf.java.sqlreflect.adapter;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;
import usf.java.sqlreflect.writer.ActionTimerWriter;

public class FullWriter<T> extends ListWriter<T> {
	
	public FullWriter(StreamWriter stream) {
		super(stream);
	}

	@Override
	public void start() throws Exception {
		getStream().startObject("ComplexType");
		super.start();
	}
	
	@Override
	public void end(ActionTimer time) throws Exception {
		super.end(time);
		new ActionTimerWriter().write(getStream(), time);
		getStream().endObject();
	}
}