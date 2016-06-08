package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.AbstractReflectorAdapter;
import usf.java.sql.reflect.core.scanner.Scanner;
import usf.java.sql.reflect.core.scanner.DatabaseScanner;

public abstract class AbstractDatabaseScanner extends AbstractReflectorAdapter implements Scanner.DatabaseAdapter {
	
	public AbstractDatabaseScanner(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void list() throws SQLException{
		new DatabaseScanner().run(this, null);
	}

	public void list(String database) throws SQLException{
		new DatabaseScanner().run(this, database);
	}
}