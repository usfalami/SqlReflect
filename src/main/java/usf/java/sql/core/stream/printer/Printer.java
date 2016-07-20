package usf.java.sql.core.stream.printer;

public interface Printer {
	
	void setHeaders(String... headers);
	
	void appendRow();
	
	void appendColumn(String value);
	
	void print();

}
