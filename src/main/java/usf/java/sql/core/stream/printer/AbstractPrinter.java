package usf.java.sql.core.stream.printer;

import java.io.OutputStream;
import java.io.PrintStream;

import usf.java.sql.adapter.formatter.Formatter;

public abstract class AbstractPrinter implements Formatter {
	
	private PrintStream stream;

	public AbstractPrinter(OutputStream out) {
		this.stream = new PrintStream(out);
	}
	
	public PrintStream getStream() {
		return stream;
	}
}
