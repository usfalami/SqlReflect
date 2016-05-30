package usf.tera.adpter.parser;

import usf.tera.adpter.Adapter;
import usf.tera.field.Procedure;
import usf.tera.field.Schema;

public interface ParserAdapter extends Adapter {


	public abstract void performSchema(Schema sc);
	public abstract void performProcedureStart(String procedure);
	public abstract void performProcedure(Procedure procedure);
	@Deprecated
	public abstract void onException(Exception e);
	
}
