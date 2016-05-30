package usf.tera.adpter;

import java.io.PrintStream;

import usf.tera.field.Parameter;
import usf.tera.field.Procedure;
import usf.tera.field.Schema;

public class ParserPrintAdapter implements ParserAdapter {

	protected Formatter f;
	
	public ParserPrintAdapter(PrintStream out) {
		f = new Formatter(out, COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH);
	}
	
	@Override
	public void performSchema(Schema sc) {
		if(sc == null) return;
		f.out.format("%-30s\n", sc.getName());
	}
	@Override
	public void performProcedureStart(String procedure) {
		f.startTable();
		f.formatTitle(procedure);
	}
	@Override
	public void performProcedure(Procedure proc) {
		if(proc == null) return;
		f.formatHeaders("N°", "Name", "Type", "Size"); 
		for(Parameter p : proc.getParameters())
			f.formatRow(p.getIndex(), p.getName(), p.getType(), p.getSize());
		f.endTable();
	}
	
	@Override
	public void onException(Exception e) {
		e.printStackTrace(f.out);
	}	
}
