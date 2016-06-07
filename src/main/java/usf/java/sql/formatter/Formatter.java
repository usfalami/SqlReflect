package usf.java.sql.formatter;

import java.io.PrintStream;

public interface Formatter {
	
	void configure(int cols, int size);
	void configure(int... sizes);
	
	void startTable();
	void formatTitle(String title);
	void formatHeaders(Object... obj);
	void formatRow(Object... obj);
	void formatFooter(String footer);
	void endTable();
	
	PrintStream getOut();

}
