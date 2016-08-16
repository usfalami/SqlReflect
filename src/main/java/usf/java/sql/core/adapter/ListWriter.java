package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.stream.StreamWriter;

public class ListWriter<T> implements ScannerAdapter<T> {

	private StreamWriter writer;
	private Mapper<T> mapper;

	public ListWriter(StreamWriter writer) {
		this.writer = writer;
	}

	@Override
	public void start() throws AdapterException {
		try {
			writer.start("LIST");
		} catch (Exception e) {
			throw new AdapterException(e);
		}
	}
	
	@Override
	public void prepare(Mapper<T> mapper) throws AdapterException {
		try {
			this.mapper = mapper;
			writer.startList("LIST", mapper.getColumnNames());
		} catch (Exception e) {
			throw new AdapterException(e);
		}
	}

	@Override
	public void adapte(T field, int index) throws AdapterException {
		try {
			mapper.write(writer, field);
		} catch (Exception e) {
			throw new AdapterException(e);
		}
	}

	@Override
	public void end() throws AdapterException {
		try {
			writer.endList();
			writer.end();
		} catch (Exception e) {
			throw new AdapterException(e);
		}
	}
	
}