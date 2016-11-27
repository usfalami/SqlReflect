package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;

public class SimpleAsciiPrinter extends AsciiPrinter<Integer> {

	private String columnPattern;

	public SimpleAsciiPrinter(OutputStream out) {
		super(out, DEFAULT_SIZE, DEFAULT_NULL_VALUE);
	}
	public SimpleAsciiPrinter(OutputStream out, Integer size) {
		super(out, size, DEFAULT_NULL_VALUE);
	}
	public SimpleAsciiPrinter(OutputStream out, Integer size, String nullValue) {
		super(out, size, nullValue);
	}
	
	@Override
	protected void init(String... columns) {
		buildLine(columns.length, getSizes());
		this.columnPattern = buildColumnPattern(getSizes());
	}

	@Override
	public void addColumn(Object value) {
		addColumn(columnPattern, value);
	}
	
}
