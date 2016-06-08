package usf.java.sql.reflect.parser.adapter;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Procedure;
import usf.java.sql.db.field.Database;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.AbstractAdapter;
import usf.java.sql.reflect.parser.ProcedureParser;
import usf.java.sql.reflect.parser.SchemaParser;

public abstract class ParserAdapter extends AbstractAdapter {

	public ParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}

	public void listSchema(String database) throws SQLException{
		new SchemaParser().run(this, database);
	}
	public void listProcedure(String database, String pattern) throws SQLException{
		new ProcedureParser().run(this, database, pattern);
	}
	
	public abstract void performDatabase(Database sc);
	public abstract void performProcedure(Procedure procedure, Column ...columns);
	
}
