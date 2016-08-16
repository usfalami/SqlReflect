package usf.java.sql.core.stream.printer;

import java.io.OutputStream;
import java.io.PrintStream;

public abstract class AbstractPrinter implements Formatter {
	
	private PrintStream stream;

	public AbstractPrinter(OutputStream out) {
		this.stream = new PrintStream(out);
	}
	
	public PrintStream getStream() {
		return stream;
	}
}
