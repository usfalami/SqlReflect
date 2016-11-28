package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;

public class SimpleAsciiPrinter extends AbstractAsciiPrinter<Integer> {

	private String columnPattern;

	public SimpleAsciiPrinter(OutputStream out) {
		super(out, DEFAULT_SIZE, DEFAULT_NULL_VALUE, DEFAULT_MARGING);
	}
	public SimpleAsciiPrinter(OutputStream out, Integer size) {
		super(out, size, DEFAULT_NULL_VALUE, DEFAULT_MARGING);
	}
	public SimpleAsciiPrinter(OutputStream out, Integer size, String nullValue) {
		super(out, size, nullValue, DEFAULT_MARGING);
	}
	public SimpleAsciiPrinter(OutputStream out, Integer sizes, String nullValue, int margin) {
		super(out, sizes, nullValue, margin);
	}

	@Override
	protected void init(String... columns) {
		int width = columns.length * (Math.abs(getSizes()) + getMarging()) 
				+ COLOMN_SEPAR.length() * (columns.length + 1);
		setTableWidth(width);
		this.columnPattern = buildColumnPattern(getSizes());
	}

	@Override
	public void addColumn(String value) {
		addColumn(columnPattern, value);
	}

}
