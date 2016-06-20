package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractReflectorAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.reflect.scanner.Scanner.HasScanner;

public class AbstractScannerAdapter extends AbstractReflectorAdapter {

	public AbstractScannerAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public static interface DatabasePrinter<T extends Database> extends HasScanner<T> {

		void list() throws SQLException;
		void list(String pattern) throws SQLException;

	}
	
	public static interface CallablePrinter<T extends Function> extends HasScanner<T> {

		void list(String database) throws SQLException;
		void list(String database, String pattern) throws SQLException;

	}
	
	public static interface CallableValidator<T extends Function> extends HasScanner<T>  {

		void validate(String callable) throws SQLException;

	}
	
	public static interface CallableComparator<T extends Function> extends HasScanner<T>  {

		void compare(String callableName) throws SQLException;

	}

}
