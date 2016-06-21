package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ColumnMapper;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureScannerPrinter extends AbstractScannerAdapter<Procedure> {
	
	public ProcedureScannerPrinter(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, new ProcedureMapper(), formatter);
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
	public void adapte(Procedure procedure, int index) {
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", procedure.getDatabase(), procedure.getName()));
		formatter.formatHeaders("N°", "Name", "Type", "Size", "As");
		formatter.startRows();

		List<?extends Column>columns = procedure.getColumns();
		if(columns== null || columns.size() == 0)
			formatter.formatFooter("This procedure has no paramters");
		else
			for(int i=0; i<columns.size(); i++){
				Column c = columns.get(i);
				formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole());
			}
		formatter.endRows();
		formatter.endTable();
	}

	@Override
	public void end() { }
	

	
	public void list(ConnectionManager cm, String database) throws SQLException{
		new ProcedureScanner(cm).run(this, new ColumnMapper(), database, null);
	}
	public void list(ConnectionManager cm, String database, String pattern) throws SQLException{
		new ProcedureScanner(cm).run(this, new ColumnMapper(), database, pattern);
	}
	
}