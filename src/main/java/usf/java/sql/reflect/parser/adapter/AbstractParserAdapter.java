package usf.java.sql.reflect.parser.adapter;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Database;
import usf.java.sql.db.field.Function;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.AbstractAdapter;
import usf.java.sql.reflect.parser.ProcedureParser;
import usf.java.sql.reflect.parser.SchemaParser;

public abstract class AbstractParserAdapter extends AbstractAdapter {

	public AbstractParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}

	public abstract void start();
	
	public abstract void finish();
	
	
	public static abstract class DatabaseParserAdapter extends AbstractParserAdapter {
		
		public DatabaseParserAdapter(ConnectionManager cm, Formatter formatter) {
			super(cm, formatter);
		}

		public void listSchema(String database) throws SQLException{
			new SchemaParser().run(this, database);
		}

		public abstract void performDatabase(Database db);
		
	}
	
	public static abstract class FunctionParserAdapter extends AbstractParserAdapter {
		
		public FunctionParserAdapter(ConnectionManager cm, Formatter formatter) {
			super(cm, formatter);
		}
		
		public void listProcedure(String database, String pattern) throws SQLException{
			new ProcedureParser().run(this, database, pattern);
		}
		
		public abstract void performFunction(Function procedure, Column ...columns);
	}
	
}
