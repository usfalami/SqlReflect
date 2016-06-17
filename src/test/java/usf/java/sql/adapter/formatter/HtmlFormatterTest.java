package usf.java.sql.adapter.formatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import junit.framework.TestCase;

public class HtmlFormatterTest extends TestCase {

	public void testStartTable() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new HtmlFormatter(o);
		format.startTable();
		assertEquals(o.toString(), "<br><table border='1'>");
		o.close();
	}
	
	public void testFormatTitle() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new HtmlFormatter(o);
		format.configure(3, 2, 1);
		String title = "my title 1";
		format.formatTitle(title);
		StringBuilder str = new StringBuilder("<tr>")
				.append("<td colspan='").append(3).append("'>")
				.append(title)
				.append("</td></tr>");
		assertEquals(o.toString(), str.toString());
		o.close();
	}
	
	public void testFormatHeaders() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new HtmlFormatter(o);
		Object[] obj = {"header 1", "header 2", "header 3", "header 4"};
		format.formatHeaders(obj);
		StringBuilder str = new StringBuilder("<tr>");
		for(Object a : obj)
			str.append("<th>").append(a).append("</th>");
		str.append("</tr>");
		assertEquals(o.toString(), str.toString());
		o.close();
	}
	
	public void testFormatRow() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new HtmlFormatter(o);
		Object[] obj = {"my row", 67, 78.7, true, new Date()};
		format.formatRow(obj);
		StringBuilder str = new StringBuilder("<tr>");
		for(Object a : obj)
			str.append("<td>").append(a).append("</td>");
		str.append("</tr>");
		assertEquals(o.toString(), str.toString());
		o.close();
	}
	
	public void testFormatFooter() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new HtmlFormatter(o);
		format.configure(3, 2, 1, 9, 50);
		String footer = "my title 1";
		format.formatFooter(footer);
		StringBuilder str = new StringBuilder("<tr>")
				.append("<td colspan='").append(5).append("'>")
				.append(footer)
				.append("</td></tr>");
		assertEquals(o.toString(), str.toString());
		o.close();
	}
	
	public void testEndTable() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new HtmlFormatter(o);
		format.endTable();
		assertEquals(o.toString(), "</table>");
		o.close();
	}
}
