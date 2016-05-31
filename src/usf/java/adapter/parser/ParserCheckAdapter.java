package usf.java.adapter.parser;

import usf.java.field.Parameter;
import usf.java.field.Procedure;
import usf.java.field.SQL;
import usf.java.formatter.Formatter;

public class ParserCheckAdapter extends ParserPrintAdapter {
	
	protected SQL sql;
		
	public ParserCheckAdapter(Formatter formatter, SQL sql){
		super(formatter);
		this.sql = sql;
		this.formatter.configure(COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH, COLUMN_PARAM_LENGTH);
	}

	@Override
	public void performProcedure(Procedure proc) {
		if(sql == null) return; // do something
		
		if(proc == null) formatter.getOut().println("Procedure not exists");
		else {
			//out.println("\t\u2713 Exist in " + proc.getSchema() + " schema");
			if(proc.getParameters().length != sql.getParameters().length)
				formatter.getOut().println("number of parameters are not equals"); //throw Exception
			else {
				//out.println("\t\u2713 Number of parameters is OK = " + proc.getParameters().length + "\n");
				//out.println("Procedure " + proc.getName() + " is OK ! \n");
				performProcedure(proc, sql);
			}
		}
	}
	protected void performProcedure(SQL base, SQL call) {
		if(base == null || call==null) return;
		formatter.startTable();
		formatter.formatTitle(call.getName());
		formatter.formatHeaders("N°", "Name", "Type", "Size", "Value"); 
		for(int i=0; i<base.getParameters().length; i++) {
			Parameter p = base.getParameters()[i];
			Parameter c = call.getParameters()[i];
			formatter.formatRow(p.getIndex(), p.getName(), p.getType(), p.getSize(), c.getValue());
		}
		formatter.endTable();
	}

}
