package usf.java.sql.formatter;

import java.io.OutputStream;

public class AsciiFormatter extends AbstractFormatter {
	
	public static final String COLOMN_SEPAR = "|";
	public static final String TABLE_CORN = "+";
	public static final char TABLE_BORDER = '-';

	private String columns, layout, row;

	public AsciiFormatter(OutputStream out) {
		super(out);
	}
	
	@Override
	public void configure(int cols, int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < cols; i++)
			s.append(COLOMN_SEPAR).append("%").append(size).append("s");
		columns = s.append(COLOMN_SEPAR).append(System.lineSeparator()).toString();
		int length = cols * (Math.abs(size) + COLOMN_SEPAR.length()) + COLOMN_SEPAR.length();
		layout = String.format(TABLE_CORN+"%" + (TABLE_CORN.length()*2-length) + "s"+TABLE_CORN+System.lineSeparator(), "").replace(' ', TABLE_BORDER);
		row = COLOMN_SEPAR+"%" + (COLOMN_SEPAR.length()*2-length) + "s"+ COLOMN_SEPAR + System.lineSeparator();
	}
	
	@Override
	public void configure(int... sizes) {
		StringBuilder s = new StringBuilder();
		int length = 0;
		for (int i = 0; i < sizes.length; i++) {
			s.append(COLOMN_SEPAR).append("%").append(sizes[i]).append("s");
			length += Math.abs(sizes[i]) + COLOMN_SEPAR.length();
		}
		columns = s.append(COLOMN_SEPAR).append(System.lineSeparator()).toString();
		length += COLOMN_SEPAR.length();
		layout = String.format(TABLE_CORN+"%" + (TABLE_CORN.length()*2-length) + "s"+TABLE_CORN+System.lineSeparator(), "").replace(' ', TABLE_BORDER);
		row = COLOMN_SEPAR+"%" + (COLOMN_SEPAR.length()*2-length) + "s"+ COLOMN_SEPAR + System.lineSeparator();
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
	}
	@Override
	public void endTable() {
		out.print(layout);
		out.println();
	}

}
