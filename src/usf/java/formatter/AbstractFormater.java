package usf.java.formatter;

import java.io.OutputStream;
import java.io.PrintStream;

public abstract class AbstractFormater implements Formatter {
	
	protected PrintStream out;

	public AbstractFormater(OutputStream out) {
		this.out = new PrintStream(out);
	}

	@Override
	public PrintStream getOut() {
		return out;
	}

}
