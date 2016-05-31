package usf.java.formatter;

import java.io.PrintStream;

public interface Formatter {

	
	void configure(int cols, int size);
	void configure(int... sizes);
	
	void startTable();
	void formatTitle(String title);
	void formatHeaders(Object... obj);
	void formatRow(Object... obj);
	void endTable();
	
	PrintStream getOut();

}
