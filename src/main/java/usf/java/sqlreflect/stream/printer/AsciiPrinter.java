package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;
import java.io.PrintStream;

import usf.java.sqlreflect.reflect.Utils;

public abstract class AsciiPrinter<T> implements Printer {
	
	public static final String COLOMN_SEPAR = "|";
	public static final String TABLE_CORN = "+";
	public static final char TABLE_BORDER = '-';
	public static final int DEFAULT_SIZE = 20;
	
	public static final String DEFAULT_NULL_VALUE = "";
	
	private PrintStream stream;
	private T sizes;
	private String line, nullValue;
	private int reverse = -1;
	
	public AsciiPrinter(OutputStream out, T sizes, String nullValue) {
		this.stream = new PrintStream(out);
		this.sizes = sizes;
		this.nullValue = nullValue;
	}
	
	@Override
	public void startList(String... columns) {
		if(Utils.isEmptyArray(columns)) throw new IllegalArgumentException();
		init(columns);
		underline();
		startObject();
		for(String s : columns) addColumn(s);
		endObject();
		underline();
	}
	@Override
	public void endList() {
		underline();
	}

	@Override
	public void startObject() {
		stream.print(COLOMN_SEPAR);
	}
	@Override
	public void endObject() {
		stream.println();
	}
	
	public void setSizes(T sizes) {
		this.sizes = sizes;
	}
	public T getSizes() {
		return sizes;
	}
	
	protected void addColumn(String pattern, Object value){
		getStream().printf(pattern, value == null ? nullValue : value);
	}

	protected abstract void init(String... columns); 
	
	public PrintStream getStream() {
		return stream;
	}
	
	protected void underline(){
		stream.println(line);
	}
	
	protected void buildLine(int cols, int size){
		int width = cols * Math.abs(size) + COLOMN_SEPAR.length() * (cols+1) - TABLE_CORN.length() * 2;
		String rowPattern = new StringBuilder(TABLE_CORN).append("%-").append(width).append("s").append(TABLE_CORN).toString();
		this.line = String.format(rowPattern, "").replace(" ", TABLE_BORDER+"");
	}
	protected void buildLineMultipleSize(int... sizes){
		int width = (sizes.length + 1) * COLOMN_SEPAR.length() - TABLE_CORN.length() * 2;
		for(int size : sizes) width += Math.abs(size);  
		String rowPattern = new StringBuilder(TABLE_CORN).append("%-").append(width).append("s").append(TABLE_CORN).toString();
		this.line =  String.format(rowPattern, "").replace(" ", TABLE_BORDER+"");
	}
	protected String buildColumnPattern(int size){
		return new StringBuilder("%").append(size * reverse).append("s").append(COLOMN_SEPAR).toString();
	}
	
	
}
