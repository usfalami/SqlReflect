package usf.java.sqlreflect.writer;

import java.sql.SQLException;
import java.util.Collection;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.sql.entry.Header;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerWriter implements Writer<ActionTimer> {

	@Override
	public void prepare(Collection<Header> headers) throws SQLException { }
	
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
	
	@Override
	public String[] getColumnNames() {
		return new String[]{"Times", "Action", "Start", "End", "Duration"};
	}
	
}