package usf.java.formatter;

import java.io.OutputStream;
import java.io.PrintStream;

public abstract class AbstractFormatter implements Formatter {
	
	protected PrintStream out;

	public AbstractFormatter(OutputStream out) {
		this.out = new PrintStream(out);
	}

	@Override
	public PrintStream getOut() {
		return out;
	}

}
