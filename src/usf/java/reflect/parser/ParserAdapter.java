package usf.java.reflect.parser;

import java.sql.SQLException;

import usf.java.adapter.Adapter;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.field.Schema;
import usf.java.formatter.Formatter;
import usf.java.reflect.ReflectFactory;

public abstract class ParserAdapter extends Adapter {
	
	private String schema, pattern; 

	public ParserAdapter(ReflectFactory rf, Formatter formatter) {
		super(rf, formatter);
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
		new ProcedureParser().run(this);
	}
	
	protected abstract void performSchema(Schema sc);
	protected abstract void performProcedure(Procedure procedure, Column ...columns);
	
}
