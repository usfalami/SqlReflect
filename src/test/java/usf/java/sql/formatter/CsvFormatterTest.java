package usf.java.sql.formatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import junit.framework.TestCase;

public class CsvFormatterTest extends TestCase {

	public void testStartTable() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new CsvFormatter(o);
		format.startTable();
		assertEquals(o.toString(), System.lineSeparator());
		o.close();
	}
	
	public void testFormatTitle() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new CsvFormatter(o);
		String title = "My Title 1";
		format.formatTitle(title);
		assertEquals(o.toString(), title+";"+System.lineSeparator());
		o.close();
	}
	
	public void testFormatHeaders() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new CsvFormatter(o);
		Object[] headers = {"header 1", "header 2", "header 3"};
		format.formatHeaders(headers);
		assertEquals(o.toString(), "header 1;header 2;header 3;"+System.lineSeparator());
		o.close();
	}
	
	public void testFormatRow() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new CsvFormatter(o);
		Date d = new Date();
		Object[] headers = {"value 1", 3.55, 5566, d, true};
		format.formatHeaders(headers);
		assertEquals(o.toString(), "value 1;3.55;5566;"+d+";true;"+System.lineSeparator());
		o.close();
	}
	
	public void testFormatFooter() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new CsvFormatter(o);
		String title = "Footer 1";
		format.formatTitle(title);
		assertEquals(o.toString(), title+";"+System.lineSeparator());
		o.close();
	}
	
	public void testEndTable() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new CsvFormatter(o);
		format.startTable();
		assertEquals(o.toString(), System.lineSeparator());
		o.close();
	}
	
}
