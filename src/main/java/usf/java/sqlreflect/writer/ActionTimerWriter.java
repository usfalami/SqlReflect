package usf.java.sqlreflect.writer;

import java.util.Arrays;
import java.util.Collection;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.Property;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerWriter implements Writer<ActionTimer> {
	
	private Collection<Property> properties = Arrays.asList(
		new Property(Constants.TIMER_ACTION), 
		new Property(Constants.TIMER_START),
		new Property(Constants.TIMER_END), 
		new Property(Constants.TIMER_DURATION)
	);

	@Override
	public void prepare(Class<? extends ActionTimer> derivedClass, Collection<Property> properties){ }
	
	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		writer.startList("Times", properties);
		recusiveWrite(writer, at, 0);
		writer.endList();
	}
	
	private void recusiveWrite(StreamWriter writer, ActionTimer action, int level) throws Exception {
		writer.startObject("ActionTimer");
		writer.writeString(Constants.TIMER_ACTION, action.getName());
		writer.writeString(Constants.TIMER_START, StreamWriter.TIME_FORMATTER.format(action.getStart()));
		writer.writeString(Constants.TIMER_END, StreamWriter.TIME_FORMATTER.format(action.getEnd()));
		writer.writeLong(Constants.TIMER_DURATION, action.duration());
		writer.endObject();
		if(Utils.isNotNull(action.getTimers()))
			for(ActionTimer t : action.getTimers())
				recusiveWrite(writer, t, level+1);
	}
	
}