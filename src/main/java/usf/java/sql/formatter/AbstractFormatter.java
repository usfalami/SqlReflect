package usf.java.sql.formatter;

import java.io.OutputStream;
import java.io.PrintStream;

public abstract class AbstractFormatter implements Formatter {
	
	protected PrintStream out;

	public AbstractFormatter(OutputStream out) {
		this.out = new PrintStream(out);
	}
}
