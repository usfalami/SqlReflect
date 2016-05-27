package usf.tera.adpter;

import usf.tera.field.Procedure;
import usf.tera.field.Schema;

public interface ParserAdapter extends Adapter {


	public abstract void performSchema(Schema sc);
	public abstract void performProcedureStart(String procedure);
	public abstract void performProcedure(Procedure sc);
	@Deprecated
	public abstract void onException(Exception e);
	
}
