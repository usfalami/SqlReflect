package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.AbstractReflectorAdapter;
import usf.java.sql.reflect.core.scanner.Scanner;
import usf.java.sql.reflect.core.scanner.ProcedureScanner;

public abstract class AbstractFunctionScnner extends AbstractReflectorAdapter implements Scanner.FunctionAdapter {
	
	public AbstractFunctionScnner(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void list(String database, String pattern) throws SQLException{
		new ProcedureScanner().run(this, database, pattern);
	}
	public void listAll(String database) throws SQLException{
		new ProcedureScanner().run(this, database, null);
	}
	
}
