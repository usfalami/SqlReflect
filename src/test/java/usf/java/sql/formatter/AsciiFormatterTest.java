package usf.java.sql.formatter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import junit.framework.TestCase;

public class AsciiFormatterTest extends TestCase {

	public void testStartTable() throws IOException {
		OutputStream o = new ByteArrayOutputStream();
		AbstractFormatter format = new AsciiFormatter(o);
		format.startTable();
		assertEquals(o.toString(), System.lineSeparator());
		o.close();
	}
	
	
}
