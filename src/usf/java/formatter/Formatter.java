package usf.java.formatter;

import java.io.PrintStream;

public interface Formatter {

	void startTable();
	void formatTitle(String title);
	void formatHeaders(Object... obj);
	void formatRow(Object... obj);
	void endTable();
	PrintStream getOut();

}
