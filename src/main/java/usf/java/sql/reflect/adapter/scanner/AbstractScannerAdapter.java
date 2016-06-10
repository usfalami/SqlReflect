package usf.java.sql.reflect.adapter.scanner;

import java.io.Serializable;
import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.AbstractReflectorAdapter;
import usf.java.sql.reflect.core.scanner.Scanner.HasDatabaseAdapter;
import usf.java.sql.reflect.core.scanner.Scanner.HasFunctionAdapter;

public class AbstractScannerAdapter extends AbstractReflectorAdapter {

	public AbstractScannerAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
	}
	
	public static interface DatabasePrinter extends HasDatabaseAdapter {

		void listDatabase() throws SQLException;
		void listDatabase(String pattern) throws SQLException;

	}
	
	public static interface Printer extends HasFunctionAdapter {

		void list(String database) throws SQLException;
		void list(String database, String pattern) throws SQLException;

	}
	
	public static interface Validator extends HasFunctionAdapter {

		void validate(String callable, Serializable... parameters) throws SQLException;

	}
	
	public static interface Comparator extends HasFunctionAdapter {

		void compare(String callableName) throws SQLException;

	}

}
