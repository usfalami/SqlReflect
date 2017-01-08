package usf.java.sqlreflect.stream;

import java.io.OutputStream;
import java.sql.Date;
import java.util.List;

import usf.java.sqlreflect.mapper.Template;
import usf.java.sqlreflect.stream.printer.DynamicAsciiPrinter;
import usf.java.sqlreflect.stream.printer.Printer;

public class PrinterStreamWriter implements StreamWriter {
		
	protected Printer printer;
	
	public PrinterStreamWriter(OutputStream out) {
		printer = new DynamicAsciiPrinter(out);
	}
	public PrinterStreamWriter(Printer printer) {
		this.printer = printer;
	}

	@Override
	public void writeBoolean(String name, Boolean bool) throws Exception {
		printer.addColumn(bool+"");
	}

	@Override
	public void writeInt(String name, Integer number) throws Exception {
		printer.addColumn(number+"");
	}

	@Override
	public void writeLong(String name, Long number) throws Exception {
		printer.addColumn(number+"");
	}

	@Override
	public void writeFloat(String name, Float number) throws Exception {
		printer.addColumn(number+"");
	}

	@Override
	public void writeDouble(String name, Double number) throws Exception {
		printer.addColumn(number+"");
	}

	@Override
	public void writeString(String name, String string) throws Exception {
		printer.addColumn(string);
	}

	@Override
	public void writeDate(String name, Date date) throws Exception {
		printer.addColumn(DATE_FORMATTER.format(date));
	}
	
	boolean list = false;
	
	@Override
	public void startObject(String name) throws Exception {
		if(list)
			printer.startRow();
	}

	@Override
	public void endObject() throws Exception {
		if(list)
			printer.endRow();
	}

	@Override
	public void startList(String name, List<Template<?>> fields) throws Exception {
		String[] columns = new String[fields.size()]; int i=0;
		for(Template<?> field : fields)
			columns[i++] = field.getName();
		printer.startTable(columns);
		list = true;
	}
	@Override
	public void endList() throws Exception {
		printer.endTable();
		list = false;
	}

	@Override
	public void start() throws Exception {}

	@Override
	public void end() throws Exception {}

}
