package usf.java.reflect.parser;

import java.sql.SQLException;

import usf.java.adapter.AbstractAdapter;
import usf.java.connection.ConnectionManager;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.field.Schema;
import usf.java.formatter.Formatter;

public abstract class ParserAdapter extends AbstractAdapter {
	
	protected String schema, pattern; 

	public ParserAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
	}
	
	public String getSchema() {
		return schema;
	}
	public String getPattern() {
		return pattern;
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
	
	protected abstract void performSchema(Schema sc);
	protected abstract void performProcedure(Procedure procedure, Column ...columns);
	
}
