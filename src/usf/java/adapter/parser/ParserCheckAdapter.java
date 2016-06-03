package usf.java.adapter.parser;

import usf.java.connection.ConnectionManager;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.field.SQL;
import usf.java.field.Schema;
import usf.java.formatter.Formatter;
import usf.java.reflect.parser.ParserAdapter;

public class ParserCheckAdapter extends ParserAdapter {
	
	protected SQL sql;
		
	public ParserCheckAdapter(ConnectionManager rf, Formatter formatter, SQL sql){
		super(rf, formatter);
		this.sql = sql;
		this.formatter.configure(COLUMN_NUM_LENGTH, COLUMN_NAME_LENGTH, COLUMN_VALUE_TYPE_LENGTH, COLUMN_SIZE_LENGTH, COLUMN_TYPE_LENGTH, COLUMN_PARAM_LENGTH);
	}

	@Override
	protected void performProcedure(Procedure proc, Column ...parameters) {
		if(sql == null) return; // do something
		
		if(proc == null) formatter.getOut().println("Procedure not exists");
		else {
			//out.println("\t\u2713 Exist in " + proc.getSchema() + " schema");
			if(parameters.length != sql.getParameters().length)
				formatter.getOut().println("number of parameters are not equals"); //throw Exception
			else {
				//out.println("\t\u2713 Number of parameters is OK = " + proc.getParameters().length + "\n");
				//out.println("Procedure " + proc.getName() + " is OK ! \n");
				performProcedure2(proc, parameters);
			}
		}
	}
	
	protected void performProcedure2(SQL base, Column ...columns) {
		if(base == null) return;
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", base.getSchema(), base.getName()));
		formatter.formatHeaders("N°", "Name", "Type", "Size", "As", "Value"); 
		for(int i=0; i<columns.length; i++) {
			Column c = columns[i];
			String p = sql.getParameters()[i];
			formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole(), p);
		}
		formatter.endTable();
	}

	@Override
	protected void performSchema(Schema sc) {
		// TODO Auto-generated method stub
		
	}

}
