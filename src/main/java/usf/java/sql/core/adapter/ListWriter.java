package usf.java.sql.core.adapter;

import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.stream.StreamWriter;

public class ListWriter<T> implements ScannerAdapter<T> {

	private Mapper<T> mapper;
	private StreamWriter writer;

	public ListWriter(Mapper<T> mapper, StreamWriter writer) {
		this.writer = writer;
		this.mapper = mapper;
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
	public void headers(String... headers) throws AdapterException {
		try {
			writer.startList("LIST", headers);
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