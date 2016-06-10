package usf.java.sql.formatter;

public interface Formatter {
	
	void configure(int cols, int size);
	void configure(int... sizes);
	
	void startTable();
	void formatTitle(String title);
	void formatHeaders(Object... obj);
	void startRows();
	void formatRow(Object... obj);
	void endRows();
	void formatFooter(String footer);
	void endTable();
	
}
