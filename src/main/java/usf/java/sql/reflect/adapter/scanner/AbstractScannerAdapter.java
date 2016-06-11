package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.AbstractReflectorAdapter;
import usf.java.sql.reflect.core.scanner.Scanner.HasDatabaseScanner;
import usf.java.sql.reflect.core.scanner.Scanner.HasCallableScanner;

public class AbstractScannerAdapter extends AbstractReflectorAdapter {

	public AbstractScannerAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
	}
	
	public static interface DatabasePrinter extends HasDatabaseScanner {

		void list() throws SQLException;
		void list(String pattern) throws SQLException;

	}
	
	public static interface Printer extends HasCallableScanner {

		void list(String database) throws SQLException;
		void list(String database, String pattern) throws SQLException;

	}
	
	public static interface Validator extends HasCallableScanner {

		void validate(String callable) throws SQLException;

	}
	
	public static interface Comparator extends HasCallableScanner {

		void compare(String callableName) throws SQLException;

	}

}
