package usf.tera.formatter;

import java.io.OutputStream;
import java.io.PrintStream;

public class AsciiFormatter implements Formatter {

	public PrintStream out;
	
	String format;
	String layout;

	public AsciiFormatter(OutputStream out, int cols, int size) {
		this.out = new PrintStream(out);
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < cols; i++)
			s.append("| %").append(size).append("s");
		format = s.append("|\n").toString();
		size = Math.abs(size);
		layout = String.format("+%" + (cols * (size + 2) - 1) + "s+\n", "").replace(' ', '-');
	}

	public AsciiFormatter(PrintStream out, int... sizes) {
		this.out = out;
		StringBuilder s = new StringBuilder();
		int max = 0;
		for (int i = 0; i < sizes.length; i++) {
			s.append("| %").append(sizes[i]).append("s");
			max += Math.abs(sizes[i]) + 2;
		}
		format = s.append("|\n").toString();
		layout = String.format("+%" + (max - 1) + "s+\n", "").replace(' ', '-');
	}

	@Override
	public void startTable() {
		out.println();
	}
	@Override
	public void formatTitle(String title) {
		out.print(layout);
		out.format("| %" + (4 - layout.length()) + "s|\n", title);
	}
	@Override
	public void formatHeaders(Object... obj) {
		out.print(layout);
		out.format(format, obj);
		out.print(layout);
	}
	@Override
	public void formatRow(Object... obj) {
		out.format(format, obj);
	}
	@Override
	public void endTable() {
		out.print(layout);
	}

	@Override
	public PrintStream getOut() {
		return out;
	}
}
