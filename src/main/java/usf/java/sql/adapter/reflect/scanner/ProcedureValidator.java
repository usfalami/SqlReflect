package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;

import usf.java.sql.adapter.formatter.Formatter;
import usf.java.sql.adapter.reflect.scanner.AbstractScannerAdapter.CallableValidator;
import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureValidator extends AbstractScannerAdapter implements CallableValidator<Procedure> {
	
	protected Function procedure;

	public ProcedureValidator(ConnectionManager cm, Formatter formatter) {
		super(cm, formatter);
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
	public void adapte(Procedure callable, Column... columns) {
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", callable.getDatabase(), callable.getName()));
		
		if(columns==null || columns.length==0) {
			if(procedure.getParameters().length == 0)
				formatter.formatFooter("This procedure has no paramters");
			else 
				formatter.formatFooter("[Error] Too many parameters : expected 0 parameters");
		}
		
		else if(procedure.getParameters().length > columns.length)
			formatter.formatFooter("[Error] Too many parameters : expected " + columns.length + " parameters");
		
		else if(procedure.getParameters().length < columns.length)
			formatter.formatFooter("[Error] Too few parameters : expected " + columns.length + " parameters");

		else {
			formatter.formatHeaders("N°", "Name", "Type", "Size", "As", "Value");
			formatter.startRows();
			String[] params = procedure.getParameters();
			int bindable = 0;
			for(int i=0; i<columns.length; i++){
				Column c = columns[i];
				formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole(), params[i]);
				if("?".equals(params[i])) bindable++;
			}
			formatter.endRows();
			formatter.formatFooter(bindable + " Bindable(s) parameter(s), " + (params.length-bindable) + " Static parameter(s)");
		}
		formatter.endTable();
	}
	
	@Override
	public void finish() { }

	@Override
	public void validate(String callable) throws SQLException {
		this.procedure = cm.getServer().parseCallable(callable);
		new ProcedureScanner().run(this, new ProcedureMapper(), procedure.getDatabase(), procedure.getName());
	}
}