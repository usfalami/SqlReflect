package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.Utils;
import usf.java.sqlreflect.stream.StreamWriter;

public class ActionTimerMapper implements Mapper<ActionTimer> {

	@Override
	public void prepare(ResultSet rs) throws SQLException {}
	
	@Override
	public ActionTimer map(ResultSet rs, int row) throws Exception {
		return new ActionTimer();
	}

	@Override
	public void write(StreamWriter writer, ActionTimer at) throws Exception {
		recusiveWrite(writer, at, 0);
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"Action", "Start", "End", "Duration"};
	}
	
	private void recusiveWrite(StreamWriter writer, ActionTimer action, int level) throws Exception {
		writer.startObject("");
		writer.writeString("Action", action.getName());
		writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(action.getStart()));
		writer.writeString("end", StreamWriter.TIME_FORMATTER.format(action.getEnd()));
		writer.writeString("Duration", String.format("%5d", action.duration()));
		writer.endObject();
		if(Utils.isNotNull(action.getTimers()))
			for(ActionTimer t : action.getTimers())
				recusiveWrite(writer, t, level+1);
	}
	
}