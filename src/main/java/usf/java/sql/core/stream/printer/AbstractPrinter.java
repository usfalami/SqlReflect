package usf.java.sql.core.stream.printer;

import java.io.OutputStream;
import java.io.PrintStream;

import usf.java.sql.adapter.formatter.Formatter;

public abstract class AbstractPrinter implements Formatter {
	
	protected PrintStream out;

	public AbstractPrinter(OutputStream out) {
		this.out = new PrintStream(out);
	}
}
