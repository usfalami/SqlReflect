package usf.tera.reflect.adpter;

import java.io.PrintStream;

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
			out.println("\t\u2713 Exist in " + proc.getSchema() + " schema");
			if(proc.getParameters().length != procedure.getParameters().length)
				out.println("number of parameters are not equals");
			else {
				out.println("\t\u2713 Number of parameters is OK = " + proc.getParameters().length + "\n");
				out.println("Procedure " + proc.getName() + " is OK ! \n");

				super.performProcedure(proc, procedure);
			}
		}
	}

}
