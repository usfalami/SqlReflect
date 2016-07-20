package usf.java.sql.adapter.formatter;

import java.io.OutputStream;

import usf.java.sql.core.stream.printer.AbstractPrinter;

public class HtmlFormatter extends AbstractPrinter {
	
	private int cols;

	public HtmlFormatter(OutputStream out) {
		super(out);
	}

	@Override
	public void configureAll(int cols, int size) {
		this.cols = cols;		
	}

	@Override
	public void configure(int... sizes) {
		this.cols = sizes.length;		
	}

	@Override
	public void startTable() {
		out.print("<br><table border='1'>");
	}

	@Override
	public void formatTitle(String title) {
		out.print("<tr>");
		out.print(new StringBuilder("<td colspan='").append(cols).append("'>")
				.append(title).append("</td>").toString());
		out.print("</tr>");
	}

	@Override
	public void formatHeaders(Object... obj) {
		out.print("<tr>");
		for(Object o : obj)
			out.print("<th>"+o+"</th>");
		out.print("</tr>");
	}

	@Override
	public void startRows() {
		out.print("<tbody>");
	}
	
	@Override
	public void formatRow(Object... obj) {
		out.print("<tr>");
		for(Object o : obj)
			out.print("<td>"+o+"</td>");
		out.print("</tr>");
	}
	
	@Override
	public void endRows() {
		out.print("</tbody>");
	}

	@Override
	public void formatFooter(String footer) {
		out.print("<tr>");
		out.print(new StringBuilder("<td colspan='").append(cols).append("'>")
				.append(footer).append("</td>").toString());
		out.print("</tr>");
	}
	
	@Override
	public void endTable() {
		out.print("</table>");
	}
}
