package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.TimePerform;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.stream.StreamWriter;

public class TimePerformWriter extends TimePerformAdapter  {

	private StreamWriter writer;
	private Mapper<TimePerform> mapper;
	
	public TimePerformWriter(Mapper<TimePerform> mapper, StreamWriter writer) {
		super();
		this.writer = writer;
		this.mapper = mapper;
	}

	@Override
	public void end() throws AdapterException {
		super.end();
		try {
			writer.start("LIST");
			writer.startList("LIST", mapper.getColumnNames());
			mapper.write(writer, time);
			writer.endList();
			writer.end();
		} catch (Exception e) {
			throw new AdapterException(e);
		}
	}
	
}