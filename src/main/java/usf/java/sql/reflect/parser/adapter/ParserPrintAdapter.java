package usf.java.sql.reflect.parser.adapter;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Database;
import usf.java.sql.db.field.Procedure;
import usf.java.sql.formatter.Formatter;

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
	public void performDatabase(Database sc) {
		if(sc == null) return;
		formatter.getOut().format("%-30s\n", sc.getName());
	}
	
	@Override
	public void performProcedure(Procedure procedure, Column ...columns) {
		if(procedure == null) return;
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", procedure.getDatabase(), procedure.getName()));
		formatter.formatHeaders("N°", "Name", "Type", "Size", "As");
		if(columns== null || columns.length == 0)
			formatter.formatFooter("This procedure has no paramters");
		else
			for(int i=0; i<columns.length; i++){
				Column c = columns[i];
				formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole());
			}
		formatter.endTable();
	}
}