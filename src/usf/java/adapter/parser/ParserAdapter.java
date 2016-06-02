package usf.java.adapter.parser;

import usf.java.adapter.Adapter;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.field.Schema;

public interface ParserAdapter extends Adapter {


	public abstract void performSchema(Schema sc);
	public abstract void performProcedure(Procedure procedure, Column ...columns);
	
}
