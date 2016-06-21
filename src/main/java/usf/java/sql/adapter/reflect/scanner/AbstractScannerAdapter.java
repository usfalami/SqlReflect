package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.AbstractReflectorAdapter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Database;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.mapper.Mapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.scanner.Scanner.HasScanner;

public class AbstractScannerAdapter<T> extends AbstractReflectorAdapter {

	protected Mapper<T> mapper;
	
	
	public AbstractScannerAdapter(SqlParser sqlParser, Mapper<T> mapper, Formatter formatter) {
		super(sqlParser, formatter);
		this.mapper = mapper;
	}
	
	public Mapper<T> getMapper() {
		return mapper;
	}
	
	public static interface DatabasePrinter<T extends Database> extends HasScanner<T> {

		void list(ConnectionManager cm) throws SQLException;
		void list(ConnectionManager cm, String pattern) throws SQLException;

	}
	
	public static interface CallablePrinter<T extends Function> extends HasScanner<T> {

		void list(ConnectionManager cm, String database) throws SQLException;
		void list(ConnectionManager cm, String database, String pattern) throws SQLException;

	}
	
	public static interface CallableValidator<T extends Function> extends HasScanner<T>  {

		void validate(ConnectionManager cm, String callable) throws SQLException;

	}
	
	public static interface CallableComparator<T extends Function> extends HasScanner<T>  {

		void compare(ConnectionManager cm, String callableName) throws SQLException;

	}

}
