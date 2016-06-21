package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ColumnMapper;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.exception.AdapterException;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureValidator extends AbstractScannerAdapter<Procedure> {
	
	protected Function callable;

	public ProcedureValidator(SqlParser sqlParser, Formatter formatter) {
		super(sqlParser, new ProcedureMapper(), formatter);
		this.formatter.configure(
				COLUMN_NUM_LENGTH, 
				COLUMN_NAME_LENGTH, 
				COLUMN_VALUE_TYPE_LENGTH, 
				COLUMN_SIZE_LENGTH, 
				COLUMN_TYPE_LENGTH,
				COLUMN_PARAM_LENGTH);
	}

	@Override
	public void start() { }

	@Override
	public void adapte(Procedure procedure, int index) {
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", procedure.getDatabase(), procedure.getName()));
		
		List<?extends Column>columns = procedure.getColumns();
		if(columns==null || columns.size()==0) {
			if(callable.getParameters().length == 0)
				formatter.formatFooter("This procedure has no paramters");
			else 
				formatter.formatFooter("[Error] Too many parameters : expected 0 parameters");
		}
		
		else if(callable.getParameters().length > columns.size())
			formatter.formatFooter("[Error] Too many parameters : expected " + columns.size() + " parameters");
		
		else if(callable.getParameters().length < columns.size())
			formatter.formatFooter("[Error] Too few parameters : expected " + columns.size() + " parameters");

		else {
			formatter.formatHeaders("N°", "Name", "Type", "Size", "As", "Value");
			formatter.startRows();
			String[] params = callable.getParameters();
			int bindable = 0;
			for(int i=0; i<columns.size(); i++){
				Column c = columns.get(i);
				formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole(), params[i]);
				if("?".equals(params[i])) bindable++;
			}
			formatter.endRows();
			formatter.formatFooter(bindable + " Bindable(s) parameter(s), " + (params.length-bindable) + " Static parameter(s)");
		}
		formatter.endTable();
	}
	
	@Override
	public void end() { }


	
	public void validate(ConnectionManager cm, String callable) throws SQLException, AdapterException {
		this.callable = sqlParser.getServer().parseCallable(callable);
		new ProcedureScanner(cm).run(this, new ColumnMapper(), this.callable.getDatabase(), this.callable.getName());
	}
}
