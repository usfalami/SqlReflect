package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.AbstractReflectorAdapter;
import usf.java.sql.reflect.core.scanner.Scanner.HasDatabaseAdapter;
import usf.java.sql.reflect.core.scanner.Scanner.HasFunctionAdapter;

public class AbstractScanner extends AbstractReflectorAdapter {

	public AbstractScanner(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
	}
	
	public static interface DatabasePrinter extends HasDatabaseAdapter {

		void listDatabase() throws SQLException;
		void listDatabase(String pattern) throws SQLException;

	}
	
	public static interface FunctionPrinter extends HasFunctionAdapter {

		void listFunction(String database) throws SQLException;
		void listFunction(String database, String pattern) throws SQLException;

	}
	
	public static interface FunctionChecker extends HasFunctionAdapter {

		void checkFunction(String callable) throws SQLException;

	}
	
	public static interface FunctionComparator extends HasFunctionAdapter {

		void compareFunction(String callableName) throws SQLException;

	}

}
