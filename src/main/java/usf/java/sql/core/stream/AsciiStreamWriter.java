package usf.java.sql.core.stream;

import java.io.OutputStream;
import java.sql.Date;

import usf.java.sql.core.stream.printer.DynamicAsciiPrinter;

public class AsciiStreamWriter implements StreamWriter {
		
	protected DynamicAsciiPrinter printer;
	
	public AsciiStreamWriter(OutputStream out) {
		printer = new DynamicAsciiPrinter(out);
	}

	@Override
	public void writeBoolean(String name, boolean bool) throws Exception {
		printer.appendColumn(""+bool);
	}

	@Override
	public void writeInt(String name, int number) throws Exception {
		printer.appendColumn(""+number);
	}

	@Override
	public void writeLong(String name, long number) throws Exception {
		printer.appendColumn(""+number);
	}

	@Override
	public void writeFloat(String name, float number) throws Exception {
		printer.appendColumn(""+number);
	}

	@Override
	public void writeDouble(String name, double number) throws Exception {
		printer.appendColumn(""+number);
	}

	@Override
	public void writeString(String name, String string) throws Exception {
		printer.appendColumn(string);
	}

	@Override
	public void writeDate(String name, Date date) throws Exception {
		printer.appendColumn(DATE_FORMATTER.format(date));
	}
	
	@Override
	public void startObject(String name) throws Exception {
		printer.appendRow();
	}

	@Override
	public void endObject() throws Exception {

	}

	@Override
	public void startList(String name, String... columns) throws Exception {
		printer.setHeaders(columns);
	}
	@Override
	public void endList() throws Exception {
		printer.print();
	}

	@Override
	public void start(String name) throws Exception {}

	@Override
	public void end() throws Exception {}

}
