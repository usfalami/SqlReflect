package usf.java.sql.adapter.formatter;

@Deprecated
public interface Formatter {
	
	void configure(int... sizes);
	void configureAll(int count, int size);
	
	void startTable();
	void formatTitle(String title);
	void formatHeaders(Object... obj);
	void startRows();
	void formatRow(Object... obj);
	void endRows();
	void formatFooter(String footer);
	void endTable();
	
}
