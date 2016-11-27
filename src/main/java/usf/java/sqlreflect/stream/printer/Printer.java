package usf.java.sqlreflect.stream.printer;

public interface Printer {

	void startList(String... columns);

	void endList();

	void startObject();

	void endObject();

	void addColumn(Object value);

}
