package usf.java.sqlreflect.adapter.reflect.scanner;

import java.util.List;

import usf.java.sqlreflect.adapter.ScannerAdapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.field.Callable;
import usf.java.sqlreflect.field.Column;
import usf.java.sqlreflect.field.Procedure;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.parser.SqlParser;
import usf.java.sqlreflect.reflect.scanner.ProcedureScanner;

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
	public void prepare(Mapper<Procedure> mapper) {
		
	}

	@Override
	public void adapte(Procedure procedure, int index) throws Exception {
		List<?extends Column>columns = procedure.getColumns();
		if(columns==null || columns.size()==0) {
			if(callable.getParameters().length == 0) 
				isValid = true;
			else 
				throw new Exception("[Error] Too many parameters : expected 0 parameters");
		}
		else if(callable.getParameters().length > columns.size())
			throw new Exception("[Error] Too many parameters : expected " + columns.size() + " parameters");
		
		else if(callable.getParameters().length < columns.size())
			throw new Exception("[Error] Too few parameters : expected " + columns.size() + " parameters");

		else isValid = true;
	}
	
	@Override
	public void end() { }
	
	public boolean validate(ConnectionManager cm, String callable) throws Exception {
		this.callable = sqlParser.getServer().parseCallable(callable);
		ProcedureScanner c = new ProcedureScanner(cm);
		c.set(this.callable.getDatabase(), this.callable.getName(), true);
		c.run(this);
		return isValid;
	}
}
