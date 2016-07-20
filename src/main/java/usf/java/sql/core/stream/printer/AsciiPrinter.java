package usf.java.sql.core.stream.printer;

import java.io.OutputStream;

public class AsciiPrinter extends AbstractPrinter {
	
	public static final String COLOMN_SEPAR = "|";
	public static final String TABLE_CORN = "+";
	public static final char TABLE_BORDER = '-';
	public static final int MARGE = 2;

	private String columns, layout, row;

	public AsciiPrinter(OutputStream out) {
		super(out);
	}
	
	@Override
	public void configureAll(int cols, int size) {
		StringBuilder s = new StringBuilder();
		//size += marge !!
		for (int i = 0; i < cols; i++)
			s.append(COLOMN_SEPAR).append("%").append(size).append("s");
		columns = s.append(COLOMN_SEPAR).append(System.lineSeparator()).toString();
		int length = cols * (Math.abs(size) + COLOMN_SEPAR.length()) + COLOMN_SEPAR.length();
		layout = String.format(TABLE_CORN+"%" + (TABLE_CORN.length()*2-length) + "s"+TABLE_CORN+System.lineSeparator(), "").replace(' ', TABLE_BORDER);
		row = COLOMN_SEPAR+"%" + (COLOMN_SEPAR.length()*2-length) + "s"+ COLOMN_SEPAR + System.lineSeparator();
	}

	public void configure(boolean inverse, int... sizes) {
		int direct = inverse ? -1 : 1; 
		StringBuilder s = new StringBuilder();
		int length = 0;
		for (int i = 0; i < sizes.length; i++) {
			int size = (sizes[i] + (sizes[i] > 0 ? MARGE : -MARGE)) * direct; 
			s.append(COLOMN_SEPAR).append("%").append(size).append("s");
			length += Math.abs(size) + COLOMN_SEPAR.length();
		}
		columns = s.append(COLOMN_SEPAR).append(System.lineSeparator()).toString();
		length += COLOMN_SEPAR.length();
		layout = String.format(TABLE_CORN+"%" + (TABLE_CORN.length()*2-length) + "s"+TABLE_CORN+System.lineSeparator(), "").replace(' ', TABLE_BORDER);
		row = COLOMN_SEPAR+"%" + (COLOMN_SEPAR.length()*2-length) + "s"+ COLOMN_SEPAR + System.lineSeparator();
	}
	
	@Override
	public void configure(int... sizes) {
		configure(false, sizes);
	}
	
	@Override
	public void startTable() {
		out.print(layout);
	}
	@Override
	public void formatTitle(String title) {
		out.format(row, title);
		out.print(layout);
	}
	@Override
	public void formatHeaders(Object... obj) {
		out.format(columns, obj);
	}
	@Override
	public void startRows() {
		out.print(layout);
	}
	@Override
	public void formatRow(Object... obj) {
		out.format(columns, obj);
	}
	@Override
	public void endRows() {
		out.print(layout);
	}
	@Override
	public void formatFooter(String footer) {
		out.format(row, footer);
		out.print(layout);
	}
	@Override
	public void endTable() {
		out.println();
	}

}
