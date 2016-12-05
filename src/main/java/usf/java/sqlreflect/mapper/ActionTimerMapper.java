package usf.java.sqlreflect.mapper;

import java.util.Map;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.stream.StreamWriter;
import usf.java.sqlreflect.writer.Writer;

public class ActionTimerMapper implements Writer<ActionTimer> {

	
	@Override
	public void prepare(Map<String, String> columnTypes) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		writer.startList("Times", "Action", "Start", "End", "Duration");
		recusiveWrite(writer, at, 0);
		writer.endList();
	}
	
	private void recusiveWrite(StreamWriter writer, ActionTimer action, int level) throws Exception {
		writer.startObject("ActionTimer");
		writer.writeString("Action", action.getName());
		writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(action.getStart()));
		writer.writeString("End", StreamWriter.TIME_FORMATTER.format(action.getEnd()));
		writer.writeLong("Duration", action.duration());
		writer.endObject();
		if(Utils.isNotNull(action.getTimers()))
			for(ActionTimer t : action.getTimers())
				recusiveWrite(writer, t, level+1);
	}
	
}