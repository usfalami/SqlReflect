package usf.java.reflect.parser.adapter;

import java.sql.SQLException;

import usf.java.connection.ConnectionManager;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.field.Schema;
import usf.java.formatter.Formatter;
import usf.java.reflect.AbstractAdapter;
import usf.java.reflect.parser.ProcedureParser;
import usf.java.reflect.parser.SchemaParser;

public abstract class ParserAdapter extends AbstractAdapter {
	
	protected String schema, pattern; 

	public ParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}

	public void listSchema(String schema) throws SQLException{
		this.schema = schema;
		new SchemaParser().run(this);
	}
	public void listProcedure(String schema, String pattern) throws SQLException{
		this.schema = schema;
		this.pattern = pattern;
		new ProcedureParser().run(this, schema);
	}
	
	public abstract void performSchema(Schema sc);
	public abstract void performProcedure(Procedure procedure, Column ...columns);
	
}