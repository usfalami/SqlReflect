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
		int width = columns.length * Math.abs(getSizes()) + COLOMN_SEPAR.length() * (columns.length+1) - TABLE_CORN.length() * 2;
		String rowPattern = new StringBuilder(TABLE_CORN).append("%-").append(width).append("s").append(TABLE_CORN).toString();
		setLinePattern(rowPattern);
		this.columnPattern = buildColumnPattern(getSizes());
	}

	@Override
	public void addColumn(String value) {
		addColumn(columnPattern, value);
	}
	
	protected void buildLine(int cols, int size){
		int width = cols * Math.abs(size) + COLOMN_SEPAR.length() * (cols+1) - TABLE_CORN.length() * 2;
		String rowPattern = new StringBuilder(TABLE_CORN).append("%-").append(width).append("s").append(TABLE_CORN).toString();
		setLinePattern(rowPattern);
	}
	
}
