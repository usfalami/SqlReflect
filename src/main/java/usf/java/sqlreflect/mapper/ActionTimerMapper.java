package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerMapper implements Mapper<ActionTimer> {

	@Override
	public ActionTimer map(ResultSet rs, int row) throws Exception {
		return null;
	}

	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		recusiveWrite(writer, at, 0);
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"Action", "Start", "End", "Duration"};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		
	}
	
	private void recusiveWrite(StreamWriter writer, ActionTimer action, int level) throws Exception {
		writer.startObject("");
		writer.writeString("Action", action.getName());
		writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(action.getStart()));
		writer.writeString("end", StreamWriter.TIME_FORMATTER.format(action.getEnd()));
		writer.writeString("Duration", String.format("%5d", action.duration()));
		writer.endObject();
		if(action.getTimers() != null)
			for(ActionTimer t : action.getTimers())
				recusiveWrite(writer, t, level+1);
	}
	
}