package usf.java.sql.adapter.formatter;

import java.io.OutputStream;

public class CsvFormatter extends AbstractFormatter {

	public CsvFormatter(OutputStream out) {
		super(out);
	}
	
	@Override
	public void configureAll(int cols, int size) {
		// TODO Auto-generated method stub
	}

	@Override
	public void configure(int... sizes) {
		// TODO Auto-generated method stub
	}

	@Override
	public void startTable() {
		out.println();
	}

	@Override
	public void formatTitle(String title) {
		out.println(title+";");
	}

	@Override
	public void formatHeaders(Object... obj) {
		this.formatRow(obj);
	}
	
	@Override
	public void startRows() {

	}

	@Override
	public void formatRow(Object... obj) {
		for(Object o : obj)
			out.print(o+";");
		out.println();
	}
	
	@Override
	public void endRows() {

	}
	
	@Override
	public void formatFooter(String footer) {
		out.println(footer+";");
	}

	@Override
	public void endTable() {
		out.println();
	}
}
