package usf.java.sql.reflect.parser.adapter;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.AbstractReflectorAdapter;
import usf.java.sql.reflect.parser.Parser;
import usf.java.sql.reflect.parser.SchemaParser;

public abstract class AbstractDatabaseParserAdapter extends AbstractReflectorAdapter implements Parser.DatabaseAdapter {
	
	public AbstractDatabaseParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public void listAll() throws SQLException{
		new SchemaParser().run(this, null);
	}

	public void list(String database) throws SQLException{
		new SchemaParser().run(this, database);
	}
}