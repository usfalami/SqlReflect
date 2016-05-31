package usf.java.adapter.parser;

import usf.java.field.Parameter;
import usf.java.field.Procedure;
import usf.java.field.Schema;
import usf.java.formatter.Formatter;

public class ParserPrintAdapter implements ParserAdapter {

	protected Formatter formatter;
	
	public ParserPrintAdapter(Formatter formatter) {
		this.formatter = formatter;
		this.formatter.configure(COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH);
	}
	
	
	@Override
	public void performSchema(Schema sc) {
		if(sc == null) return;
		formatter.getOut().format("%-30s\n", sc.getName());
	}
	
	@Override
	public void performProcedure(Procedure procedure) {
		if(procedure == null) return;
		formatter.startTable();
		formatter.formatTitle(procedure.getName());
		formatter.formatHeaders("N°", "Name", "Type", "Size"); 
		for(Parameter p : procedure.getParameters())
			formatter.formatRow(p.getIndex(), p.getName(), p.getType(), p.getSize());
		formatter.endTable();
	}
	
	@Override
	public void onException(Exception e) {
		e.printStackTrace();
	}	
}