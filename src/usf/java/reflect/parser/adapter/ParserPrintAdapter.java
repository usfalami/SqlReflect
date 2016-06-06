package usf.java.reflect.parser.adapter;

import usf.java.connection.ConnectionManager;
import usf.java.field.Column;
import usf.java.field.Procedure;
import usf.java.field.Schema;
import usf.java.formatter.Formatter;

public class ParserPrintAdapter extends ParserAdapter {
	
	public ParserPrintAdapter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_TYPE_LENGTH);
	}
	
	@Override
	public void performSchema(Schema sc) {
		if(sc == null) return;
		formatter.getOut().format("%-30s\n", sc.getName());
	}
	
	@Override
	public void performProcedure(Procedure procedure, Column ...columns) {
		if(procedure == null) return;
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", procedure.getSchema(), procedure.getName()));
		formatter.formatHeaders("N°", "Name", "Type", "Size", "As");
		if(columns!=null)
			for(int i=0; i<columns.length; i++){
				Column c = columns[i];
				formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole());
			}
		formatter.endTable();
	}
}