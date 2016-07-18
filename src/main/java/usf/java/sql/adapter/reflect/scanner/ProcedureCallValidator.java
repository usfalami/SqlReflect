package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sql.core.connection.manager.ConnectionManager;
import usf.java.sql.core.exception.AdapterException;
import usf.java.sql.core.field.Callable;
import usf.java.sql.core.field.Column;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;
import usf.java.sql.core.reflect.scanner.Scanner.ScannerAdapter;

public class ProcedureCallValidator implements ScannerAdapter<Procedure> {

	protected SqlParser sqlParser;
	
	protected Callable callable;
	protected boolean isValid;

	public ProcedureCallValidator(SqlParser sqlParser) {
		this.sqlParser = sqlParser;
	}

	@Override
	public void start() {
		isValid = false;
	}
	
	@Override
	public void headers(String... headers) {
		
	}

	@Override
	public void adapte(Procedure procedure, int index) throws AdapterException {
		List<?extends Column>columns = procedure.getColumns();
		if(columns==null || columns.size()==0) {
			if(callable.getParameters().length == 0) 
				isValid = true;
			else 
				throw new AdapterException("[Error] Too many parameters : expected 0 parameters");
		}
		else if(callable.getParameters().length > columns.size())
			throw new AdapterException("[Error] Too many parameters : expected " + columns.size() + " parameters");
		
		else if(callable.getParameters().length < columns.size())
			throw new AdapterException("[Error] Too few parameters : expected " + columns.size() + " parameters");

		else isValid = true;
	}
	
	@Override
	public void end() { }
	
	public boolean validate(ConnectionManager cm, String callable) throws SQLException, AdapterException {
		this.callable = sqlParser.getServer().parseCallable(callable);
		ProcedureScanner c = new ProcedureScanner(cm);
		c.set(this.callable.getDatabase(), this.callable.getName(), true);
		c.run(this);
		return isValid;
	}
}
