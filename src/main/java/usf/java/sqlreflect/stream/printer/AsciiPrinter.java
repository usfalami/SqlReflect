package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;
import java.io.PrintStream;

import usf.java.sqlreflect.reflect.Utils;

public abstract class AsciiPrinter<T> implements Printer {
	
	private PrintStream stream;
	private T sizes;
	private String linePattern, nullValue;
	private int reverse = -1;
	
	public AsciiPrinter(OutputStream out, T sizes, String nullValue) {
		this.stream = new PrintStream(out);
		this.sizes = sizes;
		this.nullValue = nullValue;
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
	
	protected void addColumn(String pattern, Object value){
		getStream().printf(pattern, value == null ? nullValue : value);
	}
	
	public void setSizes(T sizes) {
		this.sizes = sizes;
	}
	public T getSizes() {
		return sizes;
	}
	
	public void setNullValue(String nullValue) {
		this.nullValue = nullValue;
	}
	public String getNullValue() {
		return nullValue;
	}
	
	protected void setLinePattern(String linePattern) {
		this.linePattern = linePattern;
	}

	protected abstract void init(String... columns); 
	
	public PrintStream getStream() {
		return stream;
	}
	
	protected void underline(){
		stream.println(String.format(linePattern, "").replace(" ", TABLE_BORDER+""));
	}
	
	protected String buildColumnPattern(int size){
		return new StringBuilder("%").append(size * reverse).append("s").append(COLOMN_SEPAR).toString();
	}
	
	
}
