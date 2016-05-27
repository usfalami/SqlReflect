package usf.tera.adpter;

import java.io.PrintStream;

import usf.tera.field.Parameter;
import usf.tera.field.Procedure;
import usf.tera.field.Schema;

public class ParserPrintAdapter implements ParserAdapter {

	protected PrintStream out;
	
	public ParserPrintAdapter(PrintStream out) {
		this.out = out;
	}
	
	@Override
	public void performSchema(Schema sc) {
		if(sc == null) return;
		out.format(SCHEM_FORMAT, sc.getName());
	}
	@Override
	public void performProcedureStart(String procedure) {
		out.format(COLUMN_CADRE+PRROC_FORMAT, procedure);
	}
	@Override
	public void performProcedure(Procedure proc) {
		if(proc == null) return;
		out.print(COLUMN_CADRE+COLUMN+COLUMN_CADRE);
		for(Parameter p : proc.getParameters())
			out.format(COLUMN_FORMAT, p.getIndex(), p.getName(), p.getType(), p.getSize());
		out.println(COLUMN_CADRE);
	}
	
	@Override
	public void onException(Exception e) {
		e.printStackTrace(out);
	}	
}
