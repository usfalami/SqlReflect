package usf.java.sql.formatter;

import java.io.OutputStream;

public class AsciiFormatter extends AbstractFormatter {

	private String columns, layout, row;

	public AsciiFormatter(OutputStream out) {
		super(out);
	}
	
	@Override
	public void configure(int cols, int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < cols; i++)
			s.append("| %").append(size).append("s");
		columns = s.append("|").append(System.lineSeparator()).toString();
		int length = cols * (Math.abs(size) + 2) +1;//col size + "/ "
		layout = String.format("+%" + (2-length) + "s+"+System.lineSeparator(), "").replace(' ', '-');
		row = "| %" + (3-length) + "s|" + System.lineSeparator();
	}
	
	@Override
	public void configure(int... sizes) {
		StringBuilder s = new StringBuilder();
		int length = 1;
		for (int i = 0; i < sizes.length; i++) {
			s.append("| %").append(sizes[i]).append("s");
			length += Math.abs(sizes[i]) + 2;
		}
		columns = s.append("|").append(System.lineSeparator()).toString();
		layout = String.format("+%" + (length - 2) + "s+"+System.lineSeparator(), "").replace(' ', '-');
		row = "| %" + (3-length) + "s|" + System.lineSeparator();
	}

	@Override
	public void startTable() {
		out.println();
	}
	@Override
	public void formatTitle(String title) {
		out.print(layout);
		out.format(row, title);
	}
	@Override
	public void formatHeaders(Object... obj) {
		out.print(layout);
		out.format(columns, obj);
		out.print(layout);
	}
	@Override
	public void formatRow(Object... obj) {
		out.format(columns, obj);
	}
	@Override
	public void formatFooter(String footer) {
		out.format(row, footer);
	}
	@Override
	public void endTable() {
		out.print(layout);
	}

}
