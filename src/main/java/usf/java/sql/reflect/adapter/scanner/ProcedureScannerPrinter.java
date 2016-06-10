package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Callable;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.scanner.AbstractScannerAdapter.Printer;
import usf.java.sql.reflect.core.scanner.ProcedureScanner;

public class ProcedureScannerPrinter extends AbstractScannerAdapter implements Printer {
	
	public ProcedureScannerPrinter(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_TYPE_LENGTH);
	}

	@Override
	public void start() { }
	
	@Override
	public void adapte(Callable procedure, Column ...columns) {
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
	public void finish() { }
	
	
	@Override
	public void list(String database) throws SQLException{
		new ProcedureScanner().run(this, database, null);
	}
	@Override
	public void list(String database, String pattern) throws SQLException{
		new ProcedureScanner().run(this, database, pattern);
	}
	
}