package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.reflect.ActionPerform;
import usf.java.sqlreflect.reflect.TimePerform;
import usf.java.sqlreflect.stream.StreamWriter;

public class TimePerformMapper implements Mapper<TimePerform> {

	@Override
	public TimePerform map(ResultSet rs, int row) throws Exception {
		return null;
	}

	@Override
	public void write(StreamWriter writer, TimePerform time) throws Exception {
		final String MS_FORMAT = "%5d";
		for(ActionPerform action : time.getTimes()){
			writer.startObject("");
				writer.writeString("Action", action.getName());
				writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(action.getStart()));
				writer.writeString("end", StreamWriter.TIME_FORMATTER.format(action.getEnd()));
				writer.writeString("Duration", String.format(MS_FORMAT,action.duration()));
			writer.endObject();
		}
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"Action", "Start", "End", "Duration"};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		
	}
	
}