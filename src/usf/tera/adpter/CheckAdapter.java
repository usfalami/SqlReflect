package usf.tera.adpter;

import java.io.PrintStream;

import usf.tera.reflect.field.Procedure;

public class CheckAdapter extends PrintAdapter {
	
	protected Procedure procedure;
		
	public CheckAdapter(PrintStream out, Procedure procedure){
		super(out);
		this.procedure = procedure;
	}

	@Override
	public void performProcedure(Procedure proc) {
		if(procedure == null) return; // do something
		
		if(proc == null) System.err.println("Procedure not exists");
		else {
			System.out.println("Procedure "+ proc.getName() + " exist in " + proc.getSchema());
			if(proc.getParameters().length != procedure.getParameters().length)
				System.err.println("number of parameters are not equals");
			else {
				System.out.println("Procedure parameters are OK");
				System.out.println("Procedure " + proc.getName() + " is OK ! \n");

				super.performProcedure(proc, procedure);
			}
		}
	}

	@Override
	public void onException(Exception e) {
		e.printStackTrace();
	}

}
