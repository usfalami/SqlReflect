package usf.java.sqlreflect.writer;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerWriter implements Writer<ActionTimer> {
	
	private Collection<Metadata> metadata = Arrays.asList(
		new Metadata(Constants.TIMER_ACTION), 
		new Metadata(Constants.TIMER_START),
		new Metadata(Constants.TIMER_END), 
		new Metadata(Constants.TIMER_DURATION)
	);

	@Override
	public <D extends ActionTimer> void prepare(Class<D> derivedClass, Collection<Metadata> metadata) throws SQLException { }
	
	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		writer.startList("Times", metadata);
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