package usf.java.sql.reflect.adapter.scanner.printer;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Function;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.scanner.AbstractFunctionScnner;

public class FunctionScannerPrinter extends AbstractFunctionScnner {
	
	public FunctionScannerPrinter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_TYPE_LENGTH);
	}

	
	@Override
	public void performFunction(Function procedure, Column ...columns) {
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

	@Override
	public void start() { }
	
	@Override
	public void finish() { }
	
	
}