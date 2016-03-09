package usf.tera.adpter;

import java.io.PrintStream;

import usf.tera.reflect.field.Parameter;
import usf.tera.reflect.field.Procedure;
import usf.tera.reflect.field.Schema;

public class PrintAdapter extends Adapter {

	protected static final String SCHEM_FORMAT = "Schema : %s \n";
	protected static final String PRROC_FORMAT = "Procedure : %s \n\n";
	protected static final String PARAM_FORMAT = "[%-6s | %-30s | %-10s | %-8s]\n";
	protected static final String PARAM_VALUE_FORMAT = "[%-6s | %-30s | %-10s | %-8s| %-60s]\n";
	
	protected PrintStream out;
	
	public PrintAdapter(PrintStream out) {
		this.out = out;
	}
	
	@Override
	public void performSchema(Schema sc) {
		if(sc == null) return;
		out.format(SCHEM_FORMAT, sc.getName());
	}
	
	@Override
	public void performProcedureStart(String procedure) {
		out.format(PRROC_FORMAT, procedure);
	}
	
	@Override
	public void performProcedure(Procedure proc) {
		if(proc == null) return;
		out.format(PARAM_FORMAT, "Column","Name","Type","Size");
		for(Parameter p : proc.getParameters())
			out.format(PARAM_FORMAT, p.getIndex(), p.getName(), p.getType(), p.getSize());
		out.println();
	}
	
	@Override
	public void onException(Exception e) {
		e.printStackTrace(out);
	}	
	
	protected void performProcedure(Procedure base, Procedure call) {
		if(base == null || call==null) return;
		out.format(PARAM_VALUE_FORMAT, "Column","Name","Type","Size", "value");
		for(int i=0; i<base.getParameters().length; i++) {
			Parameter p = base.getParameters()[i];
			Parameter c = call.getParameters()[i];
			out.format(PARAM_VALUE_FORMAT, p.getIndex(), p.getName(), p.getType(), p.getSize(), c.getValue());
		}
		out.println();
	}

}
