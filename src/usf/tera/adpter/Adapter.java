package usf.tera.adpter;

import usf.tera.reflect.field.Procedure;
import usf.tera.reflect.field.Schema;

public abstract class Adapter {

	public abstract void performSchema(Schema sc);

	public abstract void performProcedureStart(String procedure);
	public abstract void performProcedure(Procedure sc);
	
	public abstract void onException(Exception e);

}
