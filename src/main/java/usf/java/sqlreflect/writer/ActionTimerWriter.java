package usf.java.sqlreflect.writer;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.Constants;
import usf.java.sqlreflect.mapper.Metadata;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerWriter implements Writer<ActionTimer> {
	
	private String[] columNames = {
		Constants.TIMER_ACTION, Constants.TIMER_START, 
		Constants.TIMER_END, Constants.TIMER_DURATION
	};

	@Override
	public void prepare(Collection<Metadata> metadata) throws SQLException { }
	
	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		writer.startList("Times", columNames);
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
	
	@Override
	public String[] getColumnNames() {
		return columNames;
	}
	
}