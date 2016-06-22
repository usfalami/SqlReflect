package usf.java.sql.adapter.reflect.scanner;

import java.sql.SQLException;
import java.util.List;

import usf.java.sql.core.connection.ConnectionManager;
import usf.java.sql.core.field.Parameter;
import usf.java.sql.core.field.Function;
import usf.java.sql.core.field.Procedure;
import usf.java.sql.core.mapper.ProcedureMapper;
import usf.java.sql.core.parser.SqlParser;
import usf.java.sql.core.reflect.exception.AdapterException;
import usf.java.sql.core.reflect.scanner.ProcedureScanner;

public class ProcedureCallValidator extends AbstractScannerAdapter<Procedure> {

	protected SqlParser sqlParser;
	protected Function callable;
	protected boolean isValid;

	public ProcedureCallValidator(SqlParser sqlParser) {
		super(new ProcedureMapper());
		this.sqlParser = sqlParser;
	}

	@Override
	public void start() {
		isValid = false;
	}

	@Override
	public void adapte(Procedure procedure, int index) throws AdapterException {
		List<?extends Parameter>columns = procedure.getColumns();
		if(columns==null || columns.size()==0) {
			if(callable.getParameters().length == 0)
				throw new AdapterException("[Error] This procedure has no paramters");
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
		new ProcedureScanner(cm).run(this, this.callable.getDatabase(), this.callable.getName(), true);
		return isValid;
	}
}
