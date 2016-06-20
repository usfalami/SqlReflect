package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.CallablePrinter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureScannerPrinter extends AbstractScannerAdapter<Procedure> implements CallablePrinter<Procedure> {
	
	public ProcedureScannerPrinter(ConnectionManager cm, Formatter formatter) {
		super(cm, new ProcedureMapper(), formatter);
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
	public void adapte(Procedure procedure) {
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", procedure.getDatabase(), procedure.getName()));
		formatter.formatHeaders("N°", "Name", "Type", "Size", "As");
		formatter.startRows();

		Column[] columns = procedure.getColumns();
		if(columns== null || columns.length == 0)
			formatter.formatFooter("This procedure has no paramters");
		else
			for(int i=0; i<columns.length; i++){
				Column c = columns[i];
				formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole());
			}
		formatter.endRows();
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