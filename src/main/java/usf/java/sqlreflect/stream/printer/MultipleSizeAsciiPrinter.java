package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;

public class MultipleSizeAsciiPrinter extends AsciiPrinter<int[]> {
	
	private int currentColumn;

	public MultipleSizeAsciiPrinter(OutputStream out) {
		super(out, null, DEFAULT_NULL_VALUE);
	}	
	public MultipleSizeAsciiPrinter(OutputStream out, int[] sizes) {
		super(out, sizes, DEFAULT_NULL_VALUE);
	}
	public MultipleSizeAsciiPrinter(OutputStream out, int[] sizes, String nullValue) {
		super(out, sizes, nullValue);
	}
	
	@Override
	protected void init(String... columns) {
		if(getSizes() == null || getSizes().length == 0){
			int[] sizes = new int[columns.length];
			for(int i=0; i<sizes.length; i++)
				sizes[i] = Math.max(Math.abs(DEFAULT_SIZE), columns[i].length());
			setSizes(sizes);
		}else if(columns.length != getSizes().length)
			throw new IllegalArgumentException();
		buildLineMultipleSize(getSizes());
	}
	
	@Override
	public void startObject() {
		super.startObject();
		this.currentColumn = 0;
	}
	
	@Override
	public void addColumn(Object value) {
		String columnPattern = buildColumnPattern(getSizes()[currentColumn++]);
		addColumn(columnPattern, value);
	}
	

}
