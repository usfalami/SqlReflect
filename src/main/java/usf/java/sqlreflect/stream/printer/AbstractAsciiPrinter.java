package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;
import java.io.PrintStream;

import usf.java.sqlreflect.reflect.Utils;

public abstract class AbstractAsciiPrinter<T> implements Printer {
	
	private PrintStream stream;
	private T sizes;
	private String line, nullValue;
	private int width, marging, reverse = -1;
	
	public AbstractAsciiPrinter(OutputStream out, T sizes, String nullValue, int margin) {
		this.stream = new PrintStream(out);
		this.sizes = sizes;
		this.nullValue = nullValue;
		this.marging = DEFAULT_MARGING;
	}
	
	@Override
	public void startTable(String... columns) {
		if(Utils.isEmptyArray(columns)) throw new IllegalArgumentException();
		init(columns);
		underline();
		startRow();
		for(String s : columns) addColumn(s);
		endRow();
		underline();
	}
	@Override
	public void endTable() {
		underline();
		stream.println();
	}

	@Override
	public void startRow() {
		stream.print(COLOMN_SEPAR);
	}
	@Override
	public void endRow() {
		stream.println();
	}

	protected void addColumn(String pattern, String value){
		getStream().printf(pattern, Utils.isNull(value) ? nullValue : value);
	}
	
	protected String buildColumnPattern(int size){
		return new StringBuilder("%").append(size * reverse).append("s")
				.append(String.format("%"+marging+"s", ""))
				.append(COLOMN_SEPAR)
				.toString();
	}
	
	protected void setTableWidth(int width) {
		this.width = width;
		String linePattern = new StringBuilder(TABLE_CORN)
				.append("%-").append(width - TABLE_CORN.length() * 2).append("s")
				.append(TABLE_CORN).toString();
		this.line = String.format(linePattern, "").replace(" ", TABLE_BORDER+"");
	}
	
	protected void setSizes(T sizes) {
		this.sizes = sizes;
	}
	protected T getSizes() {
		return sizes;
	}
	public int getMarging() {
		return marging;
	}
	
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}
	public String getNullValue() {
		return nullValue;
	}

	protected abstract void init(String... columns); 
	
	public PrintStream getStream() {
		return stream;
	}
	
	protected void underline(){
		stream.println(line);
	}
	
}
