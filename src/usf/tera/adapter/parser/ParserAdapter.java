package usf.tera.adapter.parser;

import usf.tera.adapter.Adapter;
import usf.tera.field.Procedure;
import usf.tera.field.Schema;

public interface ParserAdapter extends Adapter {


	public abstract void performSchema(Schema sc);
	public abstract void performProcedure(Procedure procedure);
	@Deprecated
	public abstract void onException(Exception e);
	
}
