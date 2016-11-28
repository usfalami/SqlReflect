package usf.java.sqlreflect.stream.printer;

import java.io.OutputStream;

import usf.java.sqlreflect.reflect.Utils;

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
		int[] sizes = getSizes();
		if(Utils.isEmptyPrimitiveArray(sizes)){
			sizes = new int[columns.length];
			for(int i=0; i<sizes.length; i++)
				sizes[i] = Math.max(Math.abs(DEFAULT_SIZE), columns[i].length());
			setSizes(sizes);
		}else 
			if(columns.length > getSizes().length) throw new IllegalArgumentException();

		int width = (sizes.length + 1) * COLOMN_SEPAR.length() - TABLE_CORN.length() * 2;
		for(int size : sizes) width += Math.abs(size);  
		String rowPattern = new StringBuilder(TABLE_CORN).append("%-").append(width).append("s").append(TABLE_CORN).toString();
		setLinePattern(rowPattern);
	}
	
	@Override
	public void startRow() {
		super.startRow();
		this.currentColumn = 0;
	}
	
	@Override
	public void addColumn(String value) {
		String columnPattern = buildColumnPattern(getSizes()[currentColumn++]);
		addColumn(columnPattern, value);
	}
	
	protected void buildLineMultipleSize(int... sizes){
	}

}
