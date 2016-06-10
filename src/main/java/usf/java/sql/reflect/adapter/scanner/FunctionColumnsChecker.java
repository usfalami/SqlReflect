package usf.java.sql.reflect.adapter.scanner;

import java.sql.SQLException;

import usf.java.sql.connection.ConnectionManager;
import usf.java.sql.db.field.Column;
import usf.java.sql.db.field.Function;
import usf.java.sql.formatter.Formatter;
import usf.java.sql.reflect.adapter.scanner.AbstractScanner.FunctionChecker;
import usf.java.sql.reflect.core.scanner.ProcedureScanner;

public class FunctionColumnsChecker extends AbstractScanner implements FunctionChecker {
	
	protected Function function;

	public FunctionColumnsChecker(ConnectionManager cm, Formatter formatter) {
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
	public void performFunction(Function function, Column... columns) {
		formatter.startTable();
		formatter.formatTitle(String.format("%s.%s", function.getDatabase(), function.getName()));
		formatter.formatHeaders("N°", "Name", "Type", "Size", "As", "Value");
		if(columns.length != this.function.getParameters().length)
			formatter.formatFooter("Error ......");
		else {
			if(columns== null || columns.length == 0)
				formatter.formatFooter("This procedure has no paramters");
			else {
				String[] params = this.function.getParameters();
				for(int i=0; i<columns.length; i++){
					Column c = columns[i];
					formatter.formatRow(i+1, c.getName(), c.getValueType(), c.getSize(), c.getRole(), params[i]);
				}
			}
		}
		formatter.endTable();
	}
	
	@Override
	public void finish() { }

	@Override
	public void checkFunction(String callable) throws SQLException {
		this.function = cm.getServer().parseFunction(callable);
		new ProcedureScanner().run(this, function.getDatabase(), function.getName());
	}
}
