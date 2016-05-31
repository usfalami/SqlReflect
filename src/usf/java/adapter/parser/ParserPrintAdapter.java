package usf.java.adapter.parser;

import usf.java.field.Parameter;
import usf.java.field.Procedure;
import usf.java.field.Schema;
import usf.java.formatter.AsciiFormatter;
import usf.java.formatter.Formatter;

public class ParserPrintAdapter implements ParserAdapter {

	protected Formatter f;
	
	public ParserPrintAdapter() {
		f = new AsciiFormatter(System.out, COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH);
	}
	
	@Override
	public void performSchema(Schema sc) {
		if(sc == null) return;
		f.getOut().format("%-30s\n", sc.getName());
	}
	
	@Override
	public void performProcedure(Procedure procedure) {
		if(procedure == null) return;
		f.startTable();
		f.formatTitle(procedure.getName());
		f.formatHeaders("N°", "Name", "Type", "Size"); 
		for(Parameter p : procedure.getParameters())
			f.formatRow(p.getIndex(), p.getName(), p.getType(), p.getSize());
		f.endTable();
	}
	
	@Override
	public void onException(Exception e) {
		e.printStackTrace();
	}	
}