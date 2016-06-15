package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractReflectorAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.reflect.scanner.Scanner.HasCallableScanner;
import usf.java.sql.core.reflect.scanner.Scanner.HasDatabaseScanner;

public class AbstractScannerAdapter extends AbstractReflectorAdapter {

	public AbstractScannerAdapter(ConnectionManager rf, Formatter formatter) {
		super(rf, formatter);
	}
	
	public static interface DatabasePrinter<T extends Database> extends HasDatabaseScanner<T> {

		void list() throws SQLException;
		void list(String pattern) throws SQLException;

	}
	
	public static interface CallablePrinter<T extends Function> extends HasCallableScanner<T> {

		void list(String database) throws SQLException;
		void list(String database, String pattern) throws SQLException;

	}
	
	public static interface CallableValidator<T extends Function> extends HasCallableScanner<T>  {

		void validate(String callable) throws SQLException;

	}
	
	public static interface CallableComparator<T extends Function> extends HasCallableScanner<T>  {

		void compare(String callableName) throws SQLException;

	}

}
