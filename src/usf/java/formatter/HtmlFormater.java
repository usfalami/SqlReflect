package usf.java.formatter;

import java.io.OutputStream;

public class HtmlFormater extends AbstractFormater {
	
	private int cols;

	public HtmlFormater(OutputStream out) {
		super(out);
	}

	@Override
	public void configure(int cols, int size) {
		this.cols = cols;		
	}

	@Override
	public void configure(int... sizes) {
		this.cols = sizes.length;		
	}

	@Override
	public void startTable() {
		out.println();
		out.println("<br>");
		out.println("<table border='1'>");
	}

	@Override
	public void formatTitle(String title) {
		out.println(" <tr>");
		out.println("  <td colspan='"+cols+"'>"+title+"</td>");
		out.println(" </tr>");
	}

	@Override
	public void formatHeaders(Object... obj) {
		out.println(" <tr>");
		for(Object o : obj)
			out.println("  <th>"+o+"</th>");
		out.println(" </tr>");
	}

	@Override
	public void formatRow(Object... obj) {
		out.println(" <tr>");
		for(Object o : obj)
			out.println("  <td>"+o+"</td>");
		out.println(" </tr>");
	}

	@Override
	public void endTable() {
		out.println("</table>");
		out.println("<br>");
	}
}
