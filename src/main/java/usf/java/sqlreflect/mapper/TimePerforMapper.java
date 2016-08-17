package usf.java.sqlreflect.mapper;

import java.sql.ResultSet;

import usf.java.sqlreflect.reflect.performer.TimePerform;
import usf.java.sqlreflect.stream.StreamWriter;

public class TimePerforMapper implements Mapper<TimePerform> {

	@Override
	public TimePerform map(ResultSet rs, int row) throws Exception {
		return null;
	}

	@Override
	public void write(StreamWriter writer, TimePerform time) throws Exception {
		final String MS_FORMAT = "%5d";
		writer.startObject("");
			writer.writeString("Action", "Connection");
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getCnxStart()));
			writer.writeString("end", StreamWriter.TIME_FORMATTER.format(time.getCnxEnd()));
			writer.writeString("Duration", String.format(MS_FORMAT,time.cnxDuration()));
		writer.endObject();
		if(time.getStatStart() !=0 && time.getStatEnd() !=0){
			writer.startObject("");
				writer.writeString("Action", "Statment");
				writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getStatStart()));
				writer.writeString("end", StreamWriter.TIME_FORMATTER.format(time.getStatEnd()));
				writer.writeString("Duration", String.format(MS_FORMAT,time.statDuration()));
			writer.endObject();
		}
		if(time.getExecStart() !=0 && time.getExecEnd() !=0){
			writer.startObject("");
				writer.writeString("Action", "Execution");
				writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getExecStart()));
				writer.writeString("end", StreamWriter.TIME_FORMATTER.format(time.getExecEnd()));
				writer.writeString("Duration", String.format(MS_FORMAT,time.execDuration()));
			writer.endObject();
		}
		if(time.getMapStart() !=0 && time.getMapEnd() !=0){
			writer.startObject("");
				writer.writeString("Action", "Mapping");
				writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getMapStart()));
				writer.writeString("end", StreamWriter.TIME_FORMATTER.format(time.getMapEnd()));
				writer.writeString("Duration", String.format(MS_FORMAT,time.mapDuration()));
			writer.endObject();
		}
		writer.startObject("");
			writer.writeString("Action", "Total");
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getStart()));
			writer.writeString("end", StreamWriter.TIME_FORMATTER.format(time.getEnd()));
			writer.writeString("Duration", String.format(MS_FORMAT,time.duration()));
		writer.endObject();
	}

	@Override
	public String[] getColumnNames() {
		return new String[]{"Action", "Start", "End", "Duration"};
	}

	@Override
	public void setColumnNames(String... columnNames) {
		
	}
	
}