package usf.java.sqlreflect.stream.printer;

public interface Printer {

	void startTable(String... columns);

	void endTable();

	void startRow();

	void endRow();

	void addColumn(String value);
	
	String COLOMN_SEPAR = "|";
	String TABLE_CORN = "+";
	char TABLE_BORDER = '-';
	int DEFAULT_SIZE = 20;
		
	String DEFAULT_NULL_VALUE = "";

}
