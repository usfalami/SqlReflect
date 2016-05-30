package usf.tera.adapter.parser;

import usf.tera.field.Parameter;
import usf.tera.field.Procedure;
import usf.tera.field.SQL;
import usf.tera.formatter.AsciiFormatter;

public class ParserCheckAdapter extends ParserPrintAdapter {
	
	protected SQL sql;
		
	public ParserCheckAdapter(SQL sql){
		this.sql = sql;
		f = new AsciiFormatter(System.out, COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_SIZE_LENGTH, COLUMN_PARAM_LENGTH);
	}

	@Override
	public void performProcedure(Procedure proc) {
		if(sql == null) return; // do something
		
		if(proc == null) f.getOut().println("Procedure not exists");
		else {
			//out.println("\t\u2713 Exist in " + proc.getSchema() + " schema");
			if(proc.getParameters().length != sql.getParameters().length)
				f.getOut().println("number of parameters are not equals"); //throw Exception
			else {
				//out.println("\t\u2713 Number of parameters is OK = " + proc.getParameters().length + "\n");
				//out.println("Procedure " + proc.getName() + " is OK ! \n");
				performProcedure(proc, sql);
			}
		}
	}
	protected void performProcedure(SQL base, SQL call) {
		if(base == null || call==null) return;
		f.formatHeaders("N°", "Name", "Type", "Size", "Value"); 
		for(int i=0; i<base.getParameters().length; i++) {
			Parameter p = base.getParameters()[i];
			Parameter c = call.getParameters()[i];
			f.formatRow(p.getIndex(), p.getName(), p.getType(), p.getSize(), c.getValue());
		}
		f.endTable();
	}

}
