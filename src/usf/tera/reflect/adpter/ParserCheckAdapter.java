package usf.tera.reflect.adpter;

import java.io.PrintStream;

import usf.tera.field.Parameter;
import usf.tera.field.Procedure;

public class ParserCheckAdapter extends ParserPrintAdapter {
	
	protected Procedure procedure;
		
	public ParserCheckAdapter(PrintStream out, Procedure procedure){
		super(out);
		this.procedure = procedure;
	}

	@Override
	public void performProcedure(Procedure proc) {
		if(procedure == null) return; // do something
		
		if(proc == null) out.println("Procedure not exists");
		else {
			//out.println("\t\u2713 Exist in " + proc.getSchema() + " schema");
			if(proc.getParameters().length != procedure.getParameters().length)
				out.println("number of parameters are not equals"); //throw Exception
			else {
				//out.println("\t\u2713 Number of parameters is OK = " + proc.getParameters().length + "\n");
				//out.println("Procedure " + proc.getName() + " is OK ! \n");
				performProcedure(proc, procedure);
			}
		}
	}
	protected void performProcedure(Procedure base, Procedure call) {
		if(base == null || call==null) return;
		out.print(COLUMN_PARAM_CADRE+COLUMN_PARAM+COLUMN_PARAM_CADRE);
		for(int i=0; i<base.getParameters().length; i++) {
			Parameter p = base.getParameters()[i];
			Parameter c = call.getParameters()[i];
			out.format(COLUMN_PARAM_FORMAT, p.getIndex(), p.getName(), p.getType(), p.getSize(), c.getValue());
		}
		out.println(COLUMN_PARAM_CADRE);
	}

}
