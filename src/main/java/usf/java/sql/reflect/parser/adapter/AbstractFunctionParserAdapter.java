package usf.java.sql.reflect.parser.adapter;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.AbstractReflectorAdapter;
import usf.java.sql.reflect.parser.Parser;
import usf.java.sql.reflect.parser.ProcedureParser;

public abstract class AbstractFunctionParserAdapter extends AbstractReflectorAdapter implements Parser.FunctionAdapter {
	
	public AbstractFunctionParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void list(String database, String pattern) throws SQLException{
		new ProcedureParser().run(this, database, pattern);
	}
	public void listAll(String database) throws SQLException{
		new ProcedureParser().run(this, database, null);
	}
	
}
