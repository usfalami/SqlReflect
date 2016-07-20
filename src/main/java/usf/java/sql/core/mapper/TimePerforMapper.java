package usf.java.sql.core.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.stream.StreamWriter;

public class TimePerforMapper implements Mapper<TimePerform> {

	@Override
	public TimePerform map(ResultSet rs, int row) throws SQLException {
		return null;
	}

	@Override
	public void write(StreamWriter writer, TimePerform time) throws Exception {
		writer.startObject("");
			writer.writeString("Action", "Connection");
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getCnxStart()));
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getCnxEnd()));
			writer.writeString("Duration", String.format("%5d ms",time.getCnxEnd()-time.getCnxStart()));
		writer.endObject();
		writer.startObject("");
			writer.writeString("Action", "Statment");
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getStatStart()));
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getStatEnd()));
			writer.writeString("Duration", String.format("%5d ms",time.getStatEnd()-time.getStatStart()));
		writer.endObject();
		writer.startObject("");
			writer.writeString("Action", "Execution");
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getExecStart()));
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getExecEnd()));
			writer.writeString("Duration", String.format("%5d ms",time.getExecEnd()-time.getExecStart()));
		writer.endObject();
		writer.startObject("");
			writer.writeString("Action", "Total");
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getStart()));
			writer.writeString("Start", StreamWriter.TIME_FORMATTER.format(time.getEnd()));
			writer.writeString("Duration", String.format("%5d ms",time.getEnd()-time.getStart()));
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