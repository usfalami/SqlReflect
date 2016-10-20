package usf.java.sqlreflect.reflect.scanner.tmp;

import java.util.Collection;

import usf.java.sqlreflect.adapter.Adapter;
import usf.java.sqlreflect.connection.manager.ConnectionManager;
import usf.java.sqlreflect.mapper.Mapper;
import usf.java.sqlreflect.parser.SqlParser;
import usf.java.sqlreflect.reflect.ActionTimer;
import usf.java.sqlreflect.reflect.scanner.field.ProcedureScanner;
import usf.java.sqlreflect.sql.item.Argument;
import usf.java.sqlreflect.sql.item.Callable;
import usf.java.sqlreflect.sql.item.Procedure;

public class ProcedureCallValidator implements Adapter<Procedure> {

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
		Collection<Argument>args = procedure.getArguments();
		if(args==null || args.size()==0) {
			if(callable.getParameters().length == 0) 
				isValid = true;
			else 
				throw new Exception("[Error] Too many parameters : expected 0 parameters");
		}
		else if(callable.getParameters().length > args.size())
			throw new Exception("[Error] Too many parameters : expected " + args.size() + " parameters");
		
		else if(callable.getParameters().length < args.size())
			throw new Exception("[Error] Too few parameters : expected " + args.size() + " parameters");

		else isValid = true;
	}
	
	@Override
	public void end(ActionTimer time) { }
	
	public boolean validate(ConnectionManager cm, String callable) throws Exception {
		this.callable = sqlParser.getServer().parseCallable(callable);
		ProcedureScanner c = new ProcedureScanner(cm);
		c.set(this.callable.getDatabaseName(), this.callable.getName(), true).run(this);
		return isValid;
	}
}
