package usf.java.sql.reflect.parser.adapter;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.field.Column;
import usf.java.sql.field.Procedure;
import usf.java.sql.field.Schema;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.AbstractAdapter;
import usf.java.sql.reflect.parser.ProcedureParser;
import usf.java.sql.reflect.parser.SchemaParser;

public abstract class ParserAdapter extends AbstractAdapter {

	public ParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}

	public void listSchema(String schema) throws SQLException{
		new SchemaParser().run(this, schema);
	}
	public void listProcedure(String schema, String pattern) throws SQLException{
		new ProcedureParser().run(this, schema, pattern);
	}
	
	public abstract void performSchema(Schema sc);
	public abstract void performProcedure(Procedure procedure, Column ...columns);
	
}
