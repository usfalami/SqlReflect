package usf.java.sql.adapter.reflect.scanner;

import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.exception.AdapterException;
import usf.java.sql.core.reflect.scanner.Scanner.ScannerAdapter;
import usf.java.sql.core.stream.StreamWriter;

public class ScannerListWriter<T> implements ScannerAdapter<T> {

	private Mapper<T> mapper;
	private StreamWriter writer;

	public ScannerListWriter(SqlParser sqlParser, Mapper<T> mapper, StreamWriter writer) {
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
			writer.end();
		} catch (Exception e) {
			throw new AdapterException(e);
		}
	}
	
}