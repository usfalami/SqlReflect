package usf.java.sqlreflect.writer;

import java.util.ArrayList;
import java.util.List;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.Utils;
import usf.java.sqlreflect.mapper.SimpleProperty;
import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerWriter implements Writer<ActionTimer> {
	
	private static final List<Template<?>> ACTIONTIMER_FIELDS;
	
	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		writer.startList("Times", ACTIONTIMER_FIELDS);
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

	static {
		ACTIONTIMER_FIELDS = new ArrayList<Template<?>>();
		ACTIONTIMER_FIELDS.add(new SimpleProperty<String>(Constants.TIMER_ACTION));
		ACTIONTIMER_FIELDS.add(new SimpleProperty<String>(Constants.TIMER_START));
		ACTIONTIMER_FIELDS.add(new SimpleProperty<String>(Constants.TIMER_END));
		ACTIONTIMER_FIELDS.add(new SimpleProperty<String>(Constants.TIMER_DURATION));
	}
}